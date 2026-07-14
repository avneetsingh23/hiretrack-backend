package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.entity.Job;

import java.util.List;

import org.springframework.data.domain.Page;

public interface JobService {

    // Add Job
    String addJob(JobRequest request);

    // Get All Jobs
    Page<Job> getAllJobs(int page,
                         int size,
                         String sortBy,
                         String direction);

    // Get Job By Id
    Job getJobById(Long id);

    // Update Job
    String updateJob(Long id, JobRequest request);

    // Delete Job
    String deleteJob(Long id);

    // Search Jobs
    List<Job> searchJobs(String keyword);
}