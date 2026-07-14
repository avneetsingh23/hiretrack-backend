package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.exception.ResourceNotFoundException;
import com.avneet.hiretrack.repository.JobRepository;
import com.avneet.hiretrack.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public String addJob(JobRequest request) {

        Job job = Job.builder()
                .title(request.getTitle())
                .company(request.getCompany())
                .location(request.getLocation())
                .jobType(request.getJobType())
                .description(request.getDescription())
                .salary(request.getSalary())
                .createdAt(LocalDateTime.now())
                .build();

        jobRepository.save(job);

        return "Job Added Successfully";
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));
    }

    @Override
    public String updateJob(Long id, JobRequest request) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());

        jobRepository.save(job);

        return "Job Updated Successfully";
    }

    @Override
    public String deleteJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        jobRepository.delete(job);

        return "Job Deleted Successfully";
    }
}