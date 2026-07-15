package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.dto.JobResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {

    // Add Job
    String addJob(JobRequest request);

    // Get All Jobs
    Page<JobResponse> getAllJobs(
            int page,
            int size,
            String sortBy,
            String direction);

    // Get Job By Id
    JobResponse getJobById(Long id);

    // Update Job
    String updateJob(Long id, JobRequest request);

    // Delete Job
    String deleteJob(Long id);

    // Search Jobs
    List<JobResponse> searchJobs(String keyword);
}