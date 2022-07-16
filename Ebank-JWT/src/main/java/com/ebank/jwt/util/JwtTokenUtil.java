package com.ebank.jwt.util;

import com.ebank.jwt.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    @Autowired
    private JwtProperties jwtProperties;

    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_USER = "sub";

    public String generateToken(String username,
                                Map<String,String> payloads) {
        logger.info("Come to generate token...");
        int payloadSizes = payloads == null? 0 : payloads.size();

        Map<String, Object> claims = new HashMap<>(payloadSizes + 2);
        claims.put(CLAIM_KEY_USER, username);
        claims.put(CLAIM_KEY_CREATED, new Date());

        if(payloadSizes > 0){
            for(Map.Entry<String,String> entry:payloads.entrySet()){
                claims.put(entry.getKey(),entry.getValue());
            }
        }
        logger.info("Generate parameters:{}",claims);
        return generateToken(claims);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + + jwtProperties.getExpiration());
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Boolean validateToken(String token, String usernameParam) {
        String username = getUsernameFromToken(token);
        return (usernameParam.equals(username) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpiredDateFromToken( token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = generateExpirationDate();
        String token = Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
        logger.info("Token generated successfully:{}",token);
        return token;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String getUsernameFromToken() {
        String username;
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            String jwtToken = request.getHeader(jwtProperties.getHeader());

            Claims claims = getClaimsFromToken(jwtToken);
            username = claims.getSubject();
        } catch (Exception e) {
            logger.error("Get the username failed,{}",e.getMessage());
            username = null;
        }
        logger.info("Get the username from token success:{}",username);
        return username;
    }
}
