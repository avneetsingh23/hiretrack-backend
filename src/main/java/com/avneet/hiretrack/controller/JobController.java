package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.ApiResponse;
import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.dto.JobResponse;
import com.avneet.hiretrack.service.JobService;
import com.avneet.hiretrack.util.SecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class JobController {

    private final JobService jobService;
    private final SecurityUtil securityUtil;

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> addJob(
            @Valid @RequestBody JobRequest request) {

        String email = securityUtil.getCurrentUserEmail();

        String message = jobService.addJob(request, email);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(message)
                        .data(null)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('USER','RECRUITER','ADMIN')")
    @GetMapping
    public Page<JobResponse> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return jobService.getAllJobs(page, size, sortBy, direction);
    }

    @PreAuthorize("hasAnyRole('USER','RECRUITER','ADMIN')")
    @GetMapping("/search")
    public List<JobResponse> searchJobs(
            @RequestParam String keyword) {

        return jobService.searchJobs(keyword);
    }

    @PreAuthorize("hasAnyRole('USER','RECRUITER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobResponse>> getJobById(
            @PathVariable Long id) {

        JobResponse job = jobService.getJobById(id);

        return ResponseEntity.ok(
                ApiResponse.<JobResponse>builder()
                        .success(true)
                        .message("Job fetched successfully")
                        .data(job)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequest request) {

        String email = securityUtil.getCurrentUserEmail();

        String message = jobService.updateJob(id, request, email);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(message)
                        .data(null)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteJob(
            @PathVariable Long id) {

        String email = securityUtil.getCurrentUserEmail();

        String message = jobService.deleteJob(id, email);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(message)
                        .data(null)
                        .build()
        );
    }
}