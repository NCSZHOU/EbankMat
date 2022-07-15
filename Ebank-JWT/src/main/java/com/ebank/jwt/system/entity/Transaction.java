package com.ebank.jwt.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Data
public class Transaction {
    String user;
    String id;
    String type;
    double amount;
    String currency;
    String iban;
    LocalDate tradetime;
    String description;
}
