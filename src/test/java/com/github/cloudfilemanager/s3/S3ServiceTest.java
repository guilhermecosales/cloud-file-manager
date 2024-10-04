package com.github.cloudfilemanager.s3;

import com.github.cloudfilemanager.configuration.CloudConfiguration;
import com.github.cloudfilemanager.s3.impl.S3ServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class S3ServiceTest {

    @Mock
    private S3Client s3Client;

    @Mock
    private CloudConfiguration cloudConfiguration;

    @InjectMocks
    private S3ServiceImpl s3Service;

    private final String bucketName = "test-bucket";

    @BeforeEach
    void setUp() {
        when(cloudConfiguration.getBucketName()).thenReturn(bucketName);
    }

    @Test
    void putObject_shouldStoreFile_whenPutValidObject() {
        // Arrange
        String key = "test-key";
        byte[] file = "test-content".getBytes();

        // Capture the request sent to the putObject method
        // ArgumentCaptor is a Mockito class that allows capturing arguments passed to a method
        ArgumentCaptor<PutObjectRequest> putObjectRequestCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
        ArgumentCaptor<RequestBody> requestBodyCaptor = ArgumentCaptor.forClass(RequestBody.class);

        // Act
        s3Service.putObject(key, file);

        // Assert
        verify(s3Client, times(1)).putObject(putObjectRequestCaptor.capture(), requestBodyCaptor.capture());

        // Verify if the request was built correctly
        PutObjectRequest capturedRequest = putObjectRequestCaptor.getValue();
        assertEquals(bucketName, capturedRequest.bucket());
        assertEquals(key, capturedRequest.key());

        // Verifying if the request body is the expected one
        RequestBody capturedRequestBody = requestBodyCaptor.getValue();
        assertEquals(file.length, capturedRequestBody.optionalContentLength().orElse(0L));
    }

}
