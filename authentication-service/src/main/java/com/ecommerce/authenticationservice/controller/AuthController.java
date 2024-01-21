package com.ecommerce.authenticationservice.controller;


import com.ecommerce.authenticationservice.entity.UserInfo;
import com.ecommerce.authenticationservice.model.request.AccountCreationRequest;
import com.ecommerce.authenticationservice.model.request.AuthenticationRequest;
import com.ecommerce.authenticationservice.model.response.AccountCreationResponse;
import com.ecommerce.authenticationservice.model.response.AuthenticationResponse;
import com.ecommerce.authenticationservice.service.AuthService;
import com.ecommerce.authenticationservice.service.CustomUserDetailsService;
import com.ecommerce.authenticationservice.utility.JwtUtil;
import com.ecommerce.authenticationservice.utility.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    private final CustomUserDetailsService customUserDetailsService;

    private final AuthService authDataService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, CustomUserDetailsService customUserDetailsService, AuthService authDataService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.authDataService = authDataService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("success");
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountCreationResponse> createAccount(
            @RequestBody AccountCreationRequest accountCreationRequest) {

        if (authDataService.findByUsername(accountCreationRequest.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new AccountCreationResponse("failure", "Username already exist"));
        }

        if (authDataService.findByEmail(accountCreationRequest.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new AccountCreationResponse("failure", "Email already exist"));
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(accountCreationRequest.getEmail());
        userInfo.setFirstName(accountCreationRequest.getFirstname());
        userInfo.setLastName(accountCreationRequest.getLastname());
        userInfo.setPassword(accountCreationRequest.getPassword());
        userInfo.setUsername(accountCreationRequest.getUsername());

        log.debug(authDataService.createUserProfile(userInfo).toString());
        return ResponseEntity.ok(new AccountCreationResponse("success", null));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestHeader(value = "Authorization") String headerData) {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        String[] data = headerData.split(" ");
        byte[] decoded = Base64.getDecoder().decode(data[1]);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        data = decodedStr.split(":");

        authenticationRequest.setUsername(data[0]);
        authenticationRequest.setPassword(data[1]);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            Md5Util.getInstance().getMd5Hash(authenticationRequest.getPassword()))
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthenticationResponse(null, "Incorrect username or password.",
                    null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthenticationResponse(null, "Username does not exist.",
                    null));
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        UserInfo userInfo = authDataService.findByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, null, userInfo.getFirstName()));
    }
}
