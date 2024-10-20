package com.github.cloudfilemanager.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestDto implements Serializable {

    @NotEmpty
    @Size(min = 3, max = 100)
    String username;

    @NotEmpty
    String password;

}
