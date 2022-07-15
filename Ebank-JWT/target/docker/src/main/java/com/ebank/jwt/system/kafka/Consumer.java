package com.ebank.jwt.system.kafka;

import com.alibaba.fastjson.JSON;
import com.ebank.jwt.config.EbankCurrency;
import com.ebank.jwt.config.KafkaConsumerProperties;
import com.ebank.jwt.config.KafkaProperties;
import com.ebank.jwt.constants.CommonConstants;
import com.ebank.jwt.system.entity.Transaction;
import com.ebank.jwt.system.entity.TransactionPage;
import com.ebank.jwt.util.Exchange;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    private EbankCurrency ebankCurrency;
    @Autowired
    private Exchange exchange;
    @Autowired
    private KafkaConsumerProperties kafkaConsumerProperties;
//    @KafkaListener(topics = {CommonConstants.TOPIC})
//    public void onMessage1(ConsumerRecord<?, ?> consumerRecord) {
//        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
//        if (optional.isPresent()) {
//            Transaction msg = (Transaction) optional.get();
//            logger.info("message:{}", msg);
//        }
//    }

    public TransactionPage fetchConsumerData() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(getProps());
        consumer.subscribe(Arrays.asList(CommonConstants.TOPIC));
        List<Transaction> list = new ArrayList<>();
        //timeout
        double credit = 0.0;
        double debit = 0.0;
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
        for (ConsumerRecord<String, String> record : records) {
            Transaction transaction = JSON.parseObject(handleJsonString(record.value()), Transaction.class);
            double rate = exchange.exchangeCurrency(transaction.getCurrency(),ebankCurrency.getCurrency());
            if(transaction.getType().equals(CommonConstants.CREDIT)) {
                credit += transaction.getAmount() * rate;
            } else {
                debit += transaction.getAmount() * rate;
            }
            list.add(transaction);
        }
        consumer.commitAsync();
        TransactionPage transactionPage = new TransactionPage();
        transactionPage.setSize(list.size());
        transactionPage.setCredit(ebankCurrency.getCurrency() + " " + exchange.scaleTwoDecimal(credit) + "-");
        transactionPage.setDebit(ebankCurrency.getCurrency() + " " + exchange.scaleTwoDecimal(debit));
        transactionPage.setTransactionList(list);
        consumer.unsubscribe();
        return transactionPage;
    }
    private String handleJsonString(String str) {
        /*
         handle JSON String like \"{\"A\":\"a\"}\"
         */
        if(!str.contains("\\")) return str;
        str = str.replaceAll("\\\\","");
        str = str.replaceFirst("\"","");
        return str.substring(0,str.length()-1);
    }
    private Properties getProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers",kafkaProperties.getBootstrapServers());
        props.put("group.id", kafkaConsumerProperties.getGroupId());
        props.put("enable.auto.commit", kafkaConsumerProperties.getEnableAutoCommit());
        props.put("auto.commit.interval.ms",kafkaConsumerProperties.getAutoCommitInterval());
        props.put("auto.offset.reset",kafkaConsumerProperties.getAutoOffsetReset());
        props.put("key.deserializer",kafkaConsumerProperties.getKeyDeserializer());
        props.put("value.deserializer",kafkaConsumerProperties.getKeyDeserializer());
        props.put("max.poll.records",kafkaConsumerProperties.getMaxPollRecords());
        return props;
    }
}
