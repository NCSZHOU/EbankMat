package com.ebank.jwt.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMyRBACService {
    @Autowired
    private MyRBACService myRBACService;
    private String userName = "Greyson";

    @Test
    public void testFindByUserName() {
        MyUserDetails myUserDetails = myRBACService.findByUserName(userName);
        Assertions.assertTrue(myUserDetails != null);
    }

    @Test
    public void testFindRoleByUserName() {
        List<String> list = myRBACService.findRoleByUserName(userName);
        Assertions.assertTrue(list.size() > 0);
    }
}
