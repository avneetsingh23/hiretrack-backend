package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.dto.DashboardResponse;
import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.entity.User;
import com.avneet.hiretrack.enums.ApplicationStatus;
import com.avneet.hiretrack.exception.ResourceNotFoundException;
import com.avneet.hiretrack.repository.ApplicationRepository;
import com.avneet.hiretrack.repository.JobRepository;
import com.avneet.hiretrack.repository.UserRepository;
import com.avneet.hiretrack.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.avneet.hiretrack.enums.Role;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public String applyJob(Long jobId, String email) {

        System.out.println("Email: " + email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        System.out.println("User Found: " + user.getEmail());

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        System.out.println("Job Found: " + job.getTitle());

        if (applicationRepository.findByUserAndJob(user, job).isPresent()) {
            System.out.println("Already Applied");
            return "You have already applied for this job";
        }

        System.out.println("Creating Application...");

        Application application = Application.builder()
                .user(user)
                .job(job)
                .status(ApplicationStatus.APPLIED)
                .appliedAt(LocalDateTime.now())
                .build();

        System.out.println("Saving Application...");

        applicationRepository.save(application);

        System.out.println("Application Saved Successfully");

        return "Job Applied Successfully";
    }

    @Override
    public List<Application> getMyApplications(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return applicationRepository.findByUser(user);
    }

    @Override
    public String withdrawApplication(Long applicationId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        if (!application.getUser().getId().equals(user.getId())) {
            return "You are not authorized to withdraw this application";
        }

        applicationRepository.delete(application);

        return "Application Withdrawn Successfully";
    }

    @Override
    public List<Application> getApplicants(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        return applicationRepository.findByJob(job);
    }
    @Override
    public List<Application> getRecruiterApplicants(String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter not found"));

        return applicationRepository.findByJobRecruiter(recruiter);
    }

    @Override
    public String updateApplicationStatus(Long applicationId,
                                          ApplicationStatus status,
                                          String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter not found"));

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Application not found"));

        // Ownership Check
        if (!application.getJob().getRecruiter().getId().equals(recruiter.getId())) {
            return "You are not authorized to update this application";
        }

        application.setStatus(status);

        applicationRepository.save(application);

        return "Application Status Updated Successfully";
    }

    @Override
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalUsers(userRepository.count())
                .totalRecruiters(userRepository.countByRole(Role.RECRUITER))
                .totalJobs(jobRepository.count())
                .totalApplications(applicationRepository.count())
                .shortlisted(applicationRepository.countByStatus(ApplicationStatus.SHORTLISTED))
                .rejected(applicationRepository.countByStatus(ApplicationStatus.REJECTED))
                .build();
    }
}