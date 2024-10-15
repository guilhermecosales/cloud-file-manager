package com.github.cloudfilemanager.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.cloudfilemanager.enumerated.Role;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.github.cloudfilemanager.entity.User}
 */
@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

    UUID userId;
    String fullName;
    String username;
    Role role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}