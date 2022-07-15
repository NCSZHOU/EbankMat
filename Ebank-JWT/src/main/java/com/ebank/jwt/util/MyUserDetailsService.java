package com.ebank.jwt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Resource
    MyRBACService myRBACService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        MyUserDetails myUserDetails = myRBACService.findByUserName(username);
        logger.info("Got the user,User's account status:{}",myUserDetails);
        myUserDetails.setPassword(new BCryptPasswordEncoder().encode(myUserDetails.getPassword()));

        List<String> roleCodes = myRBACService.findRoleByUserName(username);
        logger.info("{}'s roles:{}",roleCodes);
        List<String> authorities = new ArrayList<>();

        //The default role should have prefix "ROLE_"
        roleCodes = roleCodes.stream()
                .map(rc -> "ROLE_" +rc)
                .collect(Collectors.toList());
        authorities.addAll(roleCodes);
        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",",authorities)
                )
        );
        return myUserDetails;
    }
}
