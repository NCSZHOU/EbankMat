package com.ebank.jwt.system.common;

import com.ebank.jwt.system.entity.Transaction;
import com.ebank.jwt.system.entity.User;
import com.ebank.jwt.system.mapper.MyUserDetailsServiceMapper;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class EbankCommon {
    @Autowired
    private  MyUserDetailsServiceMapper mapper;

    private final int size = 102;
    private String generateIban(String username) {
        User user = mapper.findUserByUserName(username);
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.getByCode("CHE"))
                .bankCode("78960170")
                .accountNumber(user.getAccount())
                .build();
        return iban.toString();
    }

    public List<Transaction> generateTestDataTransactions(String username) {
        List<Transaction> transactionList = new ArrayList<>();
        Random random = new Random();
        String iban = generateIban(username);
        String type[] = new String[]{"+","-"};
        String currencies[] = new String[]{"JPY","USD","CNY","CHF","EUR","GBP","AUD","RUB","HKD"};
        for(int i=0;i<size;i++) {
            String currency = currencies[random.nextInt(currencies.length)];
            Transaction transaction = new Transaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setUser(username);
            transaction.setType(Math.random() > 0.5 ? type[0]:type[1]);
            transaction.setAmount(random.nextDouble(1000.0));
            transaction.setIban(iban);
            transaction.setTradetime(LocalDate.now().minusDays(random.nextInt(40)));
            transaction.setCurrency(currency);
            transaction.setDescription("Online payment "+currency);
            transactionList.add(transaction);
        }
        return transactionList;
    }
}
