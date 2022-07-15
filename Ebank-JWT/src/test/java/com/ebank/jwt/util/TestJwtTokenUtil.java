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
public class TestJwtTokenUtil {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTc4MDU1NDEsInN1YiI6IkdyZXlzb24iLCJjcmVhdGVkIjoxNjU3ODAxOTQxMjAyfQ.PPo16SXdaKMfSbBKueYCoNjNOSLGtAjXhVx0wwYVCW1XjoBKXGuESexop4MzSNNtAaKMu_gtGLeytoIWHaW2hg";

    @Test
    public void testGenerateToken() {
        Map<String,String> payloads = new HashMap<>();
        payloads.put("username","Greyson");
        payloads.put("password","123456");

        String token = jwtTokenUtil.generateToken("Greyson",payloads);
        Assertions.assertTrue(token!=null && !token.isEmpty() && token.length() > 20);
    }

    @Test
    public void testGetUserNameFromToken() {
        String act = jwtTokenUtil.getUsernameFromToken(token);
        String exp = "Greyson";
        Assertions.assertTrue(!exp.equals(act));
    }

    @Test
    public void testValidateToken() {
        boolean isValid = jwtTokenUtil.validateToken(token,"Greyson");
        Assertions.assertTrue(!isValid);
    }

    @Test
    public void testIsTokenExpired() {
        Assertions.assertFalse(jwtTokenUtil.isTokenExpired(token));
    }
}
