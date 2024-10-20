package com.github.cloudfilemanager.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration("securityConfiguration")
@ConfigurationProperties(prefix = "security")
public class SecurityConfiguration {

    private Jwt jwt;

    @Data
    public static class Jwt {
        private String secretKey;
        private long expirationTime;
    }

}
