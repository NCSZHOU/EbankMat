package com.ebank.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@Component
@ConfigurationProperties("ebank.jwt")
public class JwtProperties {
    private Boolean enabled;
    private String secret;
    private Long expiration;
    private String header;
    private String userParamName = "username";
    private String pwdParamName = "password";

    private List<String> corsAllowedOrigins;
    private List<String> corsAllowedMethods;
    private Boolean csrfDisabled = true;
    private Boolean useDefaultController = true;

}
