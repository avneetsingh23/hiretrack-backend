package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.RegisterRequest;
import com.avneet.hiretrack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return userService.register(request);
    }
}