package com.ebank.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerProperties {
    private String groupId;
    private String enableAutoCommit;
    private String autoCommitInterval;
    private String maxPollRecords;
    private String autoOffsetReset;
    private String keyDeserializer;
    private String valueDeserializer;
}
