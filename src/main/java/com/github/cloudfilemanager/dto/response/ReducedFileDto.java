package com.github.cloudfilemanager.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReducedFileDto implements Serializable {

    String fileName;
    LocalDateTime uploadDate;
    String fileUrl;

}