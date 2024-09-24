package com.github.cloudfilemanager.service;

import com.github.cloudfilemanager.configuration.CloudConfiguration;
import com.github.cloudfilemanager.entity.FileEntity;
import com.github.cloudfilemanager.exception.custom.FileNotFoundException;
import com.github.cloudfilemanager.repository.FileRepository;
import com.github.cloudfilemanager.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
        String mimeType = file.getContentType();

        FileEntity newFile = FileEntity.builder()
                .fileName(fileName)
                .fileUrl(getS3Url(fileName))
                .uploadDate(LocalDateTime.now())
                .fileSize(fileSize)
                .fileType(mimeType)
                .build();

        fileRepository.save(newFile);
        log.info("File metadata saved successfully.");
    }

    private String getS3Url(String fileName) {
        String bucketName = cloudConfiguration.getBucketName();

        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, fileName);
    }

    @Transactional(readOnly = true)
    public Page<FileEntity> getFilesMetadata(Pageable pageable) {
        return fileRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<FileEntity> getFileMetadataByName(@RequestParam(name = "fileName") String fileName) {
        List<FileEntity> files = fileRepository.findByFileName(fileName);
        if (files.isEmpty()) {
            throw new FileNotFoundException(fileName);
        }

        return files;
    }

    public byte[] downloadFile(String fileName) {
        log.info("Downloading file: {}", fileName);

        FileEntity storedFile = getFileMetadataByExactName(fileName);

        return s3Service.getObject(storedFile.getFileName());
    }

    @Transactional
    public void deleteFile(String fileName) {
        log.info("Deleting file: {}", fileName);

        FileEntity storedFile = getFileMetadataByExactName(fileName);

        s3Service.deleteObject(fileName);
        fileRepository.deleteById(storedFile.getFileId());

        log.info("File deleted successfully.");
    }

    private FileEntity getFileMetadataByExactName(String fileName) {
        return fileRepository.findByFileNameIgnoreCase(fileName)
                .orElseThrow(() -> new FileNotFoundException(fileName));
    }

}
