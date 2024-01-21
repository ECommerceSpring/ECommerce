package com.ecommerce.authenticationservice.model.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountCreationResponse {
    private String accountCreationStatus;
    private String errorMessage;
}
