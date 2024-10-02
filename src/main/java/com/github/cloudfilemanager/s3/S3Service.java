package com.github.cloudfilemanager.s3;

public interface S3Service {

    void putObject(String key, byte[] file);

    byte[] getObject(String keyName);

    String generatePresignedUrl(String keyName);

    void deleteObject(String keyName);

}
