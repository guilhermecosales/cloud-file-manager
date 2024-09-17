package com.github.cloudfilemanager.s3;

import com.github.cloudfilemanager.configuration.CloudConfiguration;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final Logger log = LoggerFactory.getLogger(S3Service.class);

    private final CloudConfiguration cloudConfiguration;

    private final S3Client s3Client;

    public void putObject(String key, byte[] file) {
        String bucketName = cloudConfiguration.getBucketName();
        log.info("Uploading file to S3 bucket: {}", bucketName);
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        RequestBody requestBody = RequestBody.fromBytes(file);

        s3Client.putObject(objectRequest, requestBody);
        log.info("File uploaded successfully to S3 bucket: {}", bucketName);
    }

    public byte[] getObject(String keyName) {
        String bucketName = cloudConfiguration.getBucketName();
        log.info("Downloading file from S3 bucket: {}", bucketName);
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(objectRequest);
        try {
            log.info("File downloaded successfully from S3 bucket: {}", bucketName);
            return response.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
