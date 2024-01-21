package com.ecommerce.authenticationservice.service;


import com.ecommerce.authenticationservice.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final AuthService authService;

    public CustomUserDetailsService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = authService.findByUsername(username);
        log.debug("Username = " + userInfo.getUsername() + ", Password = " + userInfo.getPassword());
        return new User(userInfo.getUsername(), userInfo.getPassword(), Collections.emptyList());
    }
}
