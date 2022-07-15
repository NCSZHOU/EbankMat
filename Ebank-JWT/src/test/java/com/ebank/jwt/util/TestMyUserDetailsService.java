package com.ebank.jwt.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMyUserDetailsService {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    private String username = "Greyson";

    @Test
    public void testLoadUserByUsername() {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        Assertions.assertTrue(userDetails!=null &&
                                       userDetails.getAuthorities().size() > 0 &&
                                       username.equals(userDetails.getUsername()) &&
                                       userDetails.isEnabled());
    }
}
