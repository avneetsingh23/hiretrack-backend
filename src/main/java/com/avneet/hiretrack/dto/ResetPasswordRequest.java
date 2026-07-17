package com.avneet.hiretrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "New Password is required")
    @Size(min = 6, max = 20)
    private String newPassword;

}