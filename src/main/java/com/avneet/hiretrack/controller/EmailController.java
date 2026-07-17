package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/test")
    public String sendTestEmail(@RequestParam String email) {

        emailService.sendEmail(
                email,
                "HireTrack Test Email",
                "Congratulations! Email configuration is working successfully."
        );

        return "Email Sent Successfully";
    }
}