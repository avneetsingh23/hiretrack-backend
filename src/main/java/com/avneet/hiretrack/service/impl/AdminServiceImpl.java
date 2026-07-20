package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.entity.User;
import com.avneet.hiretrack.exception.ResourceNotFoundException;
import com.avneet.hiretrack.repository.ApplicationRepository;
import com.avneet.hiretrack.repository.JobRepository;
import com.avneet.hiretrack.repository.UserRepository;
import com.avneet.hiretrack.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public String deleteUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Admin cannot be deleted
        if (user.getRole().name().equals("ADMIN")) {
            return "Admin cannot be deleted";
        }

        // User has applied for jobs
        if (applicationRepository.existsByUser(user)) {
            return "Cannot delete user because applications exist";
        }

        // Recruiter has posted jobs
        if (jobRepository.existsByRecruiter(user)) {
            return "Cannot delete recruiter because jobs exist";
        }

        userRepository.delete(user);

        return "User Deleted Successfully";
    }

    @Override
    public String deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        // Job has applications
        if (applicationRepository.existsByJob(job)) {
            return "Cannot delete job because applications exist";
        }

        jobRepository.delete(job);

        return "Job Deleted Successfully";
    }
}