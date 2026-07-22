package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.dto.LoginRequest;
import com.avneet.hiretrack.dto.RegisterRequest;
import com.avneet.hiretrack.entity.User;
import com.avneet.hiretrack.repository.UserRepository;
import com.avneet.hiretrack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avneet.hiretrack.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

import com.avneet.hiretrack.security.JwtService;

import com.avneet.hiretrack.util.FileUploadUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FileUploadUtil fileUploadUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid Email or Password");
        }

        return jwtService.generateToken(user.getEmail());
    }
    @Override
    public String uploadResume(String email, MultipartFile file) throws IOException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (file.isEmpty()) {
            return "Please select a file";
        }

        if (!"application/pdf".equals(file.getContentType())) {
            return "Only PDF files are allowed";
        }

        String fileName = fileUploadUtil.saveFile(file);

        user.setResumeUrl(fileName);

        userRepository.save(user);

        return "Resume Uploaded Successfully";
    }
    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}