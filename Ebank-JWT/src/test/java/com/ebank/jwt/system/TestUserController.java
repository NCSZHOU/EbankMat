package com.ebank.jwt.system;

import com.ebank.jwt.EbankJwtApplication;
import com.ebank.jwt.system.controller.TransactionController;
import com.ebank.jwt.system.controller.UserController;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EbankJwtApplication.class)
public class TestUserController {
    private final static Logger logger = LoggerFactory.getLogger(TestUserController.class);
    @Autowired
    private UserController userController;
    @Test
    public void testLogin() throws Exception {
        MockMvc mockmvc = MockMvcBuilders.standaloneSetup(userController).build();
        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/transaction/user/info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        logger.info(result.getResponse().getContentAsString());
    }

}
