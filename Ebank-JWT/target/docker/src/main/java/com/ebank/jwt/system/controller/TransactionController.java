package com.ebank.jwt.system.controller;


import com.ebank.jwt.system.entity.Transaction;
import com.ebank.jwt.system.entity.TransactionPage;
import com.ebank.jwt.system.service.TransactionService;
import com.ebank.jwt.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "TransactionController")
@RequestMapping("/transaction")
public class TransactionController {
    @Resource
    private TransactionService transactionService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * Get the transactions by login user
     * @return
     */
    @ApiOperation(value = "info")
    @GetMapping(value = "/info")
    public TransactionPage info() {
        String username = jwtTokenUtil.getUsernameFromToken();
        if(StringUtils.isEmpty(username)) return null;
        return transactionService.getPageTransactions(username);
    }
}

