package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.LoginRequest;
import com.avneet.hiretrack.dto.RegisterRequest;
import com.avneet.hiretrack.entity.User;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import com.avneet.hiretrack.dto.LoginResponse;

public interface UserService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    String uploadResume(String email, MultipartFile file) throws IOException;

    User getUserByEmail(String email);
}