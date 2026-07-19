package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.entity.User;
import com.avneet.hiretrack.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return adminService.getAllJobs();
    }

    @GetMapping("/applications")
    public List<Application> getAllApplications() {
        return adminService.getAllApplications();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return adminService.deleteUser(id);
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id) {
        return adminService.deleteJob(id);
    }
}