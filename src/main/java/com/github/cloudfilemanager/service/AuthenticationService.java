package com.github.cloudfilemanager.service;

import com.github.cloudfilemanager.dto.request.LoginRequestDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticate(LoginRequestDto input);

}
