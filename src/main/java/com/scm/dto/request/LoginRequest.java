package com.scm.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Please provide a password")
    private String password;
}
