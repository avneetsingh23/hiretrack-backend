package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.security.JwtService;
import com.avneet.hiretrack.service.ApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JwtService jwtService;

    @PostMapping("/{jobId}")
    public String applyJob(@PathVariable Long jobId,
                           HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return applicationService.applyJob(jobId, email);
    }

    @GetMapping("/my")
    public List<Application> getMyApplications(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return applicationService.getMyApplications(email);
    }

    @DeleteMapping("/{applicationId}")
    public String withdrawApplication(@PathVariable Long applicationId,
                                      HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return applicationService.withdrawApplication(applicationId, email);
    }
}