package com.ebank.jwt.system;

import com.ebank.jwt.EbankJwtApplication;
import com.ebank.jwt.system.controller.JwtAuthController;
import com.ebank.jwt.util.JwtAuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EbankJwtApplication.class)
public class TestTwtAuthController {
    private final static Logger logger = LoggerFactory.getLogger(TestTwtAuthController.class);
    @Autowired
    private JwtAuthController jwtAuthController;
    @Test
    public void testLogin() throws Exception {
        MockMvc mockmvc = MockMvcBuilders.standaloneSetup(jwtAuthController).build();
        Map<String,String> authorize = new HashMap<>();
        authorize.put("username","Greyson");
        authorize.put("password","123456");
        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/jwtauth/token",authorize))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        logger.info(result.getResponse().getContentAsString());
    }

}
