package com.github.cloudfilemanager.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseDto {

    String token;

    long expiresIn;

}
