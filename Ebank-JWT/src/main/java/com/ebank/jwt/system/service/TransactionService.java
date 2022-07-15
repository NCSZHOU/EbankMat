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
    @Autowired
    private Consumer consumer;

    public TransactionPage getPageTransactions(String username) {
        return consumer.fetchConsumerData();
    }
}
