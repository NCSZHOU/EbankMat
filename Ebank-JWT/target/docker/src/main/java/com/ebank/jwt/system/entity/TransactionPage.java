package com.ebank.jwt.system.entity;

import lombok.Data;

import java.util.List;

@Data
public class TransactionPage {
    int size;
    String credit;
    String debit;
    List<Transaction> transactionList;
}
