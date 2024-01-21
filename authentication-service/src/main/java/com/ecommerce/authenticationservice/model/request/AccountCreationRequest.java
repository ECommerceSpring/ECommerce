package com.ecommerce.authenticationservice.model.request;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountCreationRequest {
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String email;
}
