package com.avneet.hiretrack.service;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.entity.Job;

import java.util.List;

public interface JobService {

    // Add Job
    String addJob(JobRequest request);

    List<Job> getAllJobs();

    Job getJobById(Long id);

    String updateJob(Long id, JobRequest request);
    String deleteJob(Long id);
}