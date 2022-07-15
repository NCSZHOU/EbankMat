package com.ebank.jwt.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.ebank.jwt.constants.CommonConstants;
import com.ebank.jwt.system.entity.Transaction;
import com.ebank.jwt.system.mapper.MyUserDetailsServiceMapper;
import com.ebank.jwt.system.mapper.TransactionsServiceMapper;
import com.ebank.jwt.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RestController
@Api(tags = "Produce Test Data API")
@RequestMapping("/transaction")
public class ProducerController {
    private final static Logger logger = LoggerFactory.getLogger(ProducerController.class);
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Resource
    private MyUserDetailsServiceMapper userDetailsServiceMapper;
    @Resource
    private TransactionsServiceMapper transactionsServiceMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "Test Data to Kafka")
    @GetMapping("/producer")
    public void sendMessage() {
        String username = jwtTokenUtil.getUsernameFromToken();
        if(StringUtils.isEmpty(username)) return;
        String userid = userDetailsServiceMapper.getUserIdByName(username);
        List<Transaction> transactionList = transactionsServiceMapper.findTransactionsByUserName(userid);
        logger.info("Starting produce the test message for Kafka server,User:{},size:{}",username,transactionList.size());
        for(Transaction transaction:transactionList) {
            transaction.setUser(username);
            kafkaTemplate.send(CommonConstants.TOPIC, JSONObject.toJSONString(transaction));
        }
    }
}
