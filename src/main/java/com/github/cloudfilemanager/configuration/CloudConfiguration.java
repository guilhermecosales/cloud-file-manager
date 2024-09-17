package com.github.cloudfilemanager.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

@Data
@Configuration("cloudConfiguration")
@ConfigurationProperties(prefix = "aws")
public class CloudConfiguration {

    private String region;

    private String accessKey;

    private String secretKey;

    private String bucketName;

    public AwsBasicCredentials getCredentialsProvider() {
        return AwsBasicCredentials.create(accessKey, secretKey);
    }

}
