package com.avneet.hiretrack.controller;
import com.avneet.hiretrack.dto.DashboardResponse;
import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.security.JwtService;
import com.avneet.hiretrack.service.ApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.avneet.hiretrack.enums.ApplicationStatus;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JwtService jwtService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{jobId}")
    public String applyJob(@PathVariable Long jobId,
                           HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return applicationService.applyJob(jobId, email);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my")
    public List<Application> getMyApplications(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return applicationService.getMyApplications(email);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{applicationId}")
    public String withdrawApplication(@PathVariable Long applicationId,
                                      HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUsername(token);

        return applicationService.withdrawApplication(applicationId, email);
    }
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @GetMapping("/job/{jobId}")
    public List<Application> getApplicants(@PathVariable Long jobId) {
        return applicationService.getApplicants(jobId);
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @PutMapping("/{applicationId}/status")
    public String updateStatus(@PathVariable Long applicationId,
                               @RequestParam ApplicationStatus status) {

        return applicationService.updateApplicationStatus(applicationId, status);
    }
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        return applicationService.getDashboard();
    }
}