package com.avneet.hiretrack.service;

import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.enums.ApplicationStatus;

import java.util.List;
import com.avneet.hiretrack.dto.DashboardResponse;

public interface ApplicationService {

    // Apply for a Job
    String applyJob(Long jobId, String email);

    // Get all applications of logged-in user
    List<Application> getMyApplications(String email);

    // Withdraw application
    String withdrawApplication(Long applicationId, String email);

    // Get all applicants for a particular job
    List<Application> getApplicants(Long jobId);

    // NEW
    List<Application> getRecruiterApplicants(String email);

    // Update application status
    String updateApplicationStatus(Long applicationId,
                                    ApplicationStatus status,
                                    String email);

    DashboardResponse getDashboard();
}