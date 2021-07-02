package com.digitalkrapht.bloodbank.bloodbank.authentication.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank
    private String usernameOrEmail;
    private String channel ="WEB";

    @NotBlank
    private String password;
}
