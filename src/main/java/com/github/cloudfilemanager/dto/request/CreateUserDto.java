package com.github.cloudfilemanager.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cloudfilemanager.enumerated.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.github.cloudfilemanager.entity.User}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDto implements Serializable {

    @NotEmpty
    @Size(min = 3, max = 100)
    String fullName;

    @NotEmpty
    @Size(min = 3, max = 100)
    String username;

    @NotEmpty
    String password;

    @NotEmpty
    String email;

    @NotNull
    Role role;

}
