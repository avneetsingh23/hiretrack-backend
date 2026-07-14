package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.LoginRequest;
import com.avneet.hiretrack.dto.RegisterRequest;
import com.avneet.hiretrack.entity.User;   // <-- Ye import add karo

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    String register(RegisterRequest request);

    String login(LoginRequest request);

    String uploadResume(String email, MultipartFile file) throws IOException;

    User getUserByEmail(String email);
}