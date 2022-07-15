package com.ebank.jwt.util;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class Exchange {
    private Map<String,Double> currencyToCNYMap = new HashMap<>();
    public Exchange() {
        currencyToCNYMap.put("USD",6.75);
        currencyToCNYMap.put("CNY",1.0);
        currencyToCNYMap.put("JPY",0.48);
        currencyToCNYMap.put("GBP",7.99);
        currencyToCNYMap.put("EUR",6.76);
        currencyToCNYMap.put("AUD",4.55);
        currencyToCNYMap.put("HKD",0.86);
        currencyToCNYMap.put("RUB",0.11);
        currencyToCNYMap.put("CHF",6.84);
    }
    public double exchangeCurrency(String from,String to) {
        if(!currencyToCNYMap.containsKey(from) || !currencyToCNYMap.containsKey(to)) {
            return 0.0;
        }
        return scaleTwoDecimal(currencyToCNYMap.get(from) / currencyToCNYMap.get(to));

    }
    public double scaleTwoDecimal(double number) {
        BigDecimal bd = new BigDecimal(number);
        return bd.setScale(2,BigDecimal.ROUND_UP).doubleValue();
    }
}
