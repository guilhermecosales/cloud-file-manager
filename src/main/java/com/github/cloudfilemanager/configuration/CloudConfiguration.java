package com.github.cloudfilemanager.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration("cloudConfiguration")
@ConfigurationProperties(prefix = "aws")
public class CloudConfiguration {

    private String region;

}
