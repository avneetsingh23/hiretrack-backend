/*package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.ForgotPasswordRequest;
import com.avneet.hiretrack.service.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {

        return passwordResetService.sendOtp(request);
    }
}*/