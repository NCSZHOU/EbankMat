package com.ebank.jwt.system;

import com.ebank.jwt.EbankJwtApplication;
import com.ebank.jwt.system.entity.User;
import com.ebank.jwt.system.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EbankJwtApplication.class)
public class TestUserServiceImpl {
    @Autowired
    private IUserService iUserService;
    private String username = "Greyson";

    @Test
    public void testGetUserByUserName() {
        User user = iUserService.getUserByUserName(username);
        Assertions.assertTrue(user!=null &&
                                       !user.getUserName().isEmpty() &&
                                       user.getEnabled() == 1);
    }

}
