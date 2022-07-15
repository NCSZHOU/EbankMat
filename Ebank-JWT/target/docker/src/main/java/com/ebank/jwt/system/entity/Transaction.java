package com.ebank.jwt.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class Transaction {
    String user;
    String transactionId= UUID.randomUUID().toString();
    String type;
    double amount;
    String currency;
    String iban;
    String tradeDateTime;
    String description;
}
