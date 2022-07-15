package com.ebank.jwt.util;

import cn.hutool.core.util.StrUtil;
import com.ebank.jwt.config.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private JwtProperties jwtProperties;
    private JwtTokenUtil jwtTokenUtil;
    private MyUserDetailsService myUserDetailsService;

    public JwtAuthenticationTokenFilter(JwtProperties jwtProperties,
                                        JwtTokenUtil jwtTokenUtil,
                                        MyUserDetailsService myUserDetailsService) {
        this.jwtProperties = jwtProperties;
        this.jwtTokenUtil = jwtTokenUtil;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //get token for global validation
        String authHeader = request.getHeader(jwtProperties.getHeader());
        if (!StrUtil.isEmpty(authHeader)) {
            String username = jwtTokenUtil.getUsernameFromToken(authHeader);
            logger.info("checking username:"+ username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authHeader, username)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    logger.info("authenticated user:"+username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
