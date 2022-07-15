package com.ebank.jwt.util;

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

    @Resource
    MyRBACService myRBACService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        MyUserDetails myUserDetails = myRBACService.findByUserName(username);
        myUserDetails.setPassword(new BCryptPasswordEncoder().encode(myUserDetails.getPassword()));

        List<String> roleCodes = myRBACService.findRoleByUserName(username);
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
