package com.ebank.jwt.system.service;

import com.ebank.jwt.system.entity.Transaction;
import com.ebank.jwt.system.entity.TransactionPage;
import com.ebank.jwt.system.kafka.Consumer;
import com.ebank.jwt.system.mapper.MyUserDetailsServiceMapper;
import com.ebank.jwt.system.mapper.TransactionsServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Resource
    private MyUserDetailsServiceMapper userDetailsServiceMapper;
    @Autowired
    private Consumer consumer;

    @Resource
    private TransactionsServiceMapper transactionsServiceMapper;
    public TransactionPage getPageTransactions(String username) {
//        String userid = userDetailsServiceMapper.getUserIdByName(username);
//        List<Transaction> transactionList = transactionsServiceMapper.findTransactionsByUserName(userid);
//        for(Transaction transaction:transactionList) {
//            transaction.setUser(username);
//        }

        return consumer.fetchConsumerData();
    }
}
