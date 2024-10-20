package com.github.cloudfilemanager.service.impl;

import com.github.cloudfilemanager.dto.request.LoginRequestDto;
import com.github.cloudfilemanager.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @Override
    public UserDetails authenticate(LoginRequestDto input) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                input.getUsername(),
                input.getPassword()
        );

        authenticationManager.authenticate(token);

        return userDetailsService.loadUserByUsername(input.getUsername());
    }

}
