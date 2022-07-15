package com.ebank.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("com.ebank.jwt.*.mapper")
@SpringBootApplication
public class EbankJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbankJwtApplication.class, args);
    }
}
