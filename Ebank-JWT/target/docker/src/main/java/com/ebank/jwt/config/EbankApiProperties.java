package com.ebank.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "ebank.api")
public class EbankApiProperties {
    public static final String[] ENDPOINTS = {
            "/jwtauth/**",
            "/swagger-ui/swagger-resources/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/kafka/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
    };
    private List<String> ignoreUrl = new ArrayList<>();
    @PostConstruct
    public void initIgnoreUrl() {
        Collections.addAll(ignoreUrl, ENDPOINTS);
    }
}
