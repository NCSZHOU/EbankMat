package com.ebank.jwt.system.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

import javax.annotation.PostConstruct;

@Configuration
public class KafkaListener {
    private final static Logger logger = LoggerFactory.getLogger(KafkaListener.class);
    @Autowired
    KafkaTemplate kafkaTemplate;

    @PostConstruct
    private void listener() {
        kafkaTemplate.setProducerListener(new ProducerListener<String, Object>() {
            @Override
            public void onSuccess(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata) {
                logger.info("ok,message={}", producerRecord.value());
            }

            public void onError(ProducerRecord<String, Object> producerRecord, Exception exception) {
                logger.error("error!message={}", producerRecord.value());
            }
        });
    }
}
