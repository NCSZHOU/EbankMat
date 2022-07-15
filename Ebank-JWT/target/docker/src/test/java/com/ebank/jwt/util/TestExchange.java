package com.ebank.jwt.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestExchange {
    @Autowired
    private Exchange exchange;

    @Test
    public void testExchangeCurrencyUSDAndEUR() {
        String usd = "USD";
        String eur = "EUR";

        double act = exchange.exchangeCurrency(usd,eur);
        double exp = 1.0;
        Assertions.assertTrue(act==exp);
    }

    @Test
    public void testExchangeCurrencyCHFAndGBP() {
        String chf = "CHF";
        String gbp = "GBP";

        double act = exchange.exchangeCurrency(chf,gbp);
        double exp = 0.86;
        Assertions.assertTrue(act==exp);
    }

    @Test
    public void testScaleTwoDecimal() {
        double target = 5.079069586;
        double exp = 5.08;
        Assertions.assertTrue(exp ==exchange.scaleTwoDecimal(target));
    }
}
