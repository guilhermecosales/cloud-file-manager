package com.github.cloudfilemanager.controller;

import com.github.cloudfilemanager.dto.common.UserDto;
import com.github.cloudfilemanager.dto.request.CreateUserDto;
import com.github.cloudfilemanager.entity.User;
import com.github.cloudfilemanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
