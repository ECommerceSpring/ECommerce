package com.ecommerce.authenticationservice.model.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {

    private String username;
    private String password;
}
