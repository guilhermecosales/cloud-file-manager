package com.github.cloudfilemanager.service;

import com.github.cloudfilemanager.configuration.CloudConfiguration;
import com.github.cloudfilemanager.entity.FileEntity;
import com.github.cloudfilemanager.repository.FileRepository;
import com.github.cloudfilemanager.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Logger log = LoggerFactory.getLogger(FileService.class);

    private final CloudConfiguration cloudConfiguration;

    private final FileRepository fileRepository;

    private final S3Service s3Service;

    public void uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        log.info("Uploading file: {}", fileName);

        try {
            s3Service.putObject(fileName, file.getBytes());
            saveMetadataFile(file);
            log.info("File uploaded successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveMetadataFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        double fileSize = file.getSize();

        FileEntity newFile = FileEntity.builder()
                .fileName(fileName)
                .fileUrl(getS3Url(fileName))
                .uploadDate(LocalDateTime.now())
                .fileSize(fileSize)
                .fileType(getFileExtension(fileName))
                .build();

        fileRepository.save(newFile);
        log.info("File metadata saved successfully.");
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String getS3Url(String fileName) {
        String region = cloudConfiguration.getRegion();
        String bucketName = cloudConfiguration.getBucketName();

        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, fileName);
    }

}
