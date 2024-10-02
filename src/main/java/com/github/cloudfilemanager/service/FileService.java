package com.github.cloudfilemanager.service;

import com.github.cloudfilemanager.entity.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFile(MultipartFile file);

    Page<FileEntity> getFilesMetadata(Pageable pageable);

    List<FileEntity> getFileMetadataByName(@RequestParam(name = "fileName") String fileName);

    byte[] downloadFile(String fileName);

    String downloadFileUrl(String fileName);

    void deleteFile(String fileName);

}
