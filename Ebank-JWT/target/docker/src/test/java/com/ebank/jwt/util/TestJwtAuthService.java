package com.ebank.jwt.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestJwtAuthService {
    @Autowired
    private JwtAuthService jwtAuthService;

    @Test
    public void testLoginSuccess() {
        String username = "Greyson";
        String password = "123456";
        Map<String,String> payloads = new HashMap<>();
        String token = jwtAuthService.login(username,password,payloads);
        Assertions.assertTrue(!token.isEmpty() && token.length() > 20);
    }

    @Test
    public void testRefresh() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTc4MDU1NDEsInN1YiI6IkdyZXlzb24iLCJjcmVhdGVkIjoxNjU3ODAxOTQxMjAyfQ.PPo16SXdaKMfSbBKueYCoNjNOSLGtAjXhVx0wwYVCW1XjoBKXGuESexop4MzSNNtAaKMu_gtGLeytoIWHaW2hg";
        String refreshedToken = jwtAuthService.refreshToken(token);
        Assertions.assertTrue(!refreshedToken.isEmpty() && refreshedToken.length() > 20 && !refreshedToken.equals(token));
    }
}
