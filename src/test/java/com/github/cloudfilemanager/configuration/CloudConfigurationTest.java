package com.github.cloudfilemanager.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class CloudConfigurationTest {

    @Autowired
    private CloudConfiguration cloudConfiguration;

    @Test
    void testCloudConfigurationLoads() {
        assertNotNull(cloudConfiguration.getRegion());
        assertNotNull(cloudConfiguration.getAccessKey());
        assertNotNull(cloudConfiguration.getSecretKey());
        assertNotNull(cloudConfiguration.getBucketName());
    }

}
