package com.github.cloudfilemanager.service;

import com.github.cloudfilemanager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User saveUser(User user);

    Page<User> findAll(Pageable pageable);

}
