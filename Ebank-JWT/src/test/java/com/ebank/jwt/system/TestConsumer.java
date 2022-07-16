package com.ebank.jwt.system;

import com.ebank.jwt.EbankJwtApplication;
import com.ebank.jwt.system.controller.ProducerController;
import com.ebank.jwt.system.entity.TransactionPage;
import com.ebank.jwt.system.kafka.Consumer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EbankJwtApplication.class)
public class TestConsumer {
    @Autowired
    private Consumer consumer;
    @Test
    public void testFetchConsumerData()  {
        TransactionPage transactionPage = consumer.fetchConsumerData();
        Assertions.assertTrue(transactionPage != null && transactionPage.getTransactionList().size() > 0);
    }
}
