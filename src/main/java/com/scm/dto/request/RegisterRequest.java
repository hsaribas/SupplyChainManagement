package com.scm.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Size(max = 100)
    @NotBlank(message = "Please provide your name")
    private String name;

    @Size(min = 8, max = 20, message = "Please provide correct size for password")
    @NotBlank(message = "Please provide your password")
    private String password;

    @Email(message = "Please provide a valid email")
    @Size(min = 5, max = 80)
    private String email;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$", message = "Please provide valid phone number")
    @Size(min = 12, max = 12)
    @NotBlank(message = "Please provide your phone number")
    private String phoneNumber;
}
