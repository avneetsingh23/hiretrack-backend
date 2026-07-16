package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.dto.JobResponse;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.exception.ResourceNotFoundException;
import com.avneet.hiretrack.repository.JobRepository;
import com.avneet.hiretrack.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.avneet.hiretrack.entity.User;
import com.avneet.hiretrack.enums.Role;
import com.avneet.hiretrack.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public String addJob(JobRequest request, String email){

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Job job = Job.builder()
                .title(request.getTitle())
                .company(request.getCompany())
                .location(request.getLocation())
                .jobType(request.getJobType())
                .description(request.getDescription())
                .salary(request.getSalary())
                .createdAt(LocalDateTime.now())
                .recruiter(recruiter)
                .build();

        jobRepository.save(job);

        return "Job Added Successfully";
    }

    @Override
    public Page<JobResponse> getAllJobs(int page,
                                        int size,
                                        String sortBy,
                                        String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return jobRepository.findAll(pageRequest)
                .map(this::mapToResponse);
    }

    @Override
    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        return mapToResponse(job);
    }

    @Override
    public String updateJob(Long id, JobRequest request, String email){

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !job.getRecruiter().getId().equals(currentUser.getId())) {

            throw new org.springframework.security.access.AccessDeniedException(
                    "Access Denied");
        }

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
    public String deleteJob(Long id, String email) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (currentUser.getRole() != Role.ADMIN &&
                !job.getRecruiter().getId().equals(currentUser.getId())) {

            throw new org.springframework.security.access.AccessDeniedException(
                    "Access Denied");
        }

        jobRepository.delete(job);

        return "Job Deleted Successfully";
    }
    @Override
    public List<JobResponse> searchJobs(String keyword) {

        return jobRepository
                .findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrLocationContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private JobResponse mapToResponse(Job job) {

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .description(job.getDescription())
                .salary(job.getSalary())
                .createdAt(job.getCreatedAt())
                .build();
    }
}