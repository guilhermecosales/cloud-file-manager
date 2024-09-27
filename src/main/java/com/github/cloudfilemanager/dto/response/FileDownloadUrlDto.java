package com.github.cloudfilemanager.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.io.Serializable;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDownloadUrlDto implements Serializable {

    String downloadUrl;

}