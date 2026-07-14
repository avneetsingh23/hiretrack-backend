package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.service.UserService;
import com.avneet.hiretrack.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.avneet.hiretrack.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/upload-resume")
    public String uploadResume(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("file") MultipartFile file) throws IOException {

        String token = authHeader.substring(7);

        String email = jwtService.extractUsername(token);

        return userService.uploadResume(email, file);
    }
    @GetMapping("/resume")
    public ResponseEntity<Resource> downloadResume(
            @RequestHeader("Authorization") String authHeader) throws Exception {

        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        User user = userService.getUserByEmail(email);

        Path path = Paths.get("uploads/resumes")
                .resolve(user.getResumeUrl());

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}