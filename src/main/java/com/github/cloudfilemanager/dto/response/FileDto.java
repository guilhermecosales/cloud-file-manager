package com.github.cloudfilemanager.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto implements Serializable {

    UUID fileId;
    String fileName;
    String fileUrl;
    LocalDateTime uploadDate;
    double fileSize;
    String fileType;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}