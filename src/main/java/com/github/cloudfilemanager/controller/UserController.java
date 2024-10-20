package com.github.cloudfilemanager.controller;

import com.github.cloudfilemanager.dto.common.UserDto;
import com.github.cloudfilemanager.dto.request.CreateUserDto;
import com.github.cloudfilemanager.entity.User;
import com.github.cloudfilemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody CreateUserDto request) {

        // TODO: implement the mapper for the request and response objects

        User newUser = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        newUser = userService.saveUser(newUser);

        UserDto response = UserDto.builder()
                .userId(newUser.getUserId())
                .fullName(newUser.getFullName())
                .username(newUser.getUsername())
                .role(newUser.getRole())
                .createdAt(newUser.getCreatedAt())
                .updatedAt(newUser.getUpdatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> authenticatedUser() {
        User currentAuthenticateUser = getCurrentAuthenticateUser();

        log.info("Current user: {}", currentAuthenticateUser);

        UserDto response = UserDto.builder()
                .userId(currentAuthenticateUser.getUserId())
                .fullName(currentAuthenticateUser.getFullName())
                .username(currentAuthenticateUser.getUsername())
                .role(currentAuthenticateUser.getRole())
                .createdAt(currentAuthenticateUser.getCreatedAt())
                .updatedAt(currentAuthenticateUser.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    private static User getCurrentAuthenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated.");
        }

        return (User) authentication.getPrincipal();
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(Pageable pageable) {
        Page<User> users = userService.findAll(pageable);

        Page<UserDto> response = users.map(user -> UserDto.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
