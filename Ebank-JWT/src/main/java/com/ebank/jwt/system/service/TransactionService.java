package com.ebank.jwt.system.service;

import com.ebank.jwt.system.entity.TransactionPage;
import com.ebank.jwt.system.kafka.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private Consumer consumer;

    public TransactionPage getPageTransactions(String username) {
        return consumer.fetchConsumerData();
    }
}
