package com.github.cloudfilemanager.controller;

import com.github.cloudfilemanager.dto.request.LoginRequestDto;
import com.github.cloudfilemanager.dto.response.LoginResponseDto;
import com.github.cloudfilemanager.service.AuthenticationService;
import com.github.cloudfilemanager.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        UserDetails authenticatedUser = authenticationService.authenticate(loginRequestDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponseDto = new LoginResponseDto(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponseDto);
    }
}
