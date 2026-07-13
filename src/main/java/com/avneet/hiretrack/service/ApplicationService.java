package com.avneet.hiretrack.service;

import com.avneet.hiretrack.entity.Application;

import java.util.List;

public interface ApplicationService {

    // Apply for a Job
    String applyJob(Long jobId, String email);

    // Get all applications of logged-in user
    List<Application> getMyApplications(String email);

    // Withdraw application
    String withdrawApplication(Long applicationId, String email);
}