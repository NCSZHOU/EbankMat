package com.ebank.jwt.util;

import com.ebank.commons.BaseException;
import com.ebank.commons.api.Imp.ResultCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthService {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private MyRBACService myRBACService;

    private JwtAuthService(){}

    public JwtAuthService(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * login the page to get the jwt token
     * @return JWT
     */
    public String login(String username,
                        String password,
                        Map<String,String> payloads) throws BaseException {
        try {
            List<String> role = myRBACService.findRoleByUserName(username);
            role = role.stream()
                    .map(rc -> "ROLE_" +rc)
                    .collect(Collectors.toList());
            List<GrantedAuthority> roleCodes = AuthorityUtils.createAuthorityList(role.toArray(String[]::new));
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username, password,roleCodes);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (AuthenticationException e){
            throw new BaseException(ResultCode.FAILURE.getCode()
                    ,"Wrong Username or password, or it is new user or this user have no right");
        }

        return jwtTokenUtil.generateToken(username,payloads);
    }

    public String refreshToken(String oldToken){
        if(!jwtTokenUtil.isTokenExpired(oldToken)){
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
