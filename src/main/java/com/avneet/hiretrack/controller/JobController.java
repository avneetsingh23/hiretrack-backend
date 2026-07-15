package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.ApiResponse;
import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.dto.JobResponse;
import com.avneet.hiretrack.service.JobService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addJob(
            @Valid @RequestBody JobRequest request) {

        String message = jobService.addJob(request);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(message)
                        .data(null)
                        .build()
        );
    }

    @GetMapping
    public Page<JobResponse> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return jobService.getAllJobs(page, size, sortBy, direction);
    }

    @GetMapping("/search")
    public List<JobResponse> searchJobs(
            @RequestParam String keyword) {

        return jobService.searchJobs(keyword);
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequest request) {

        String message = jobService.updateJob(id, request);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(message)
                        .data(null)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteJob(
            @PathVariable Long id) {

        String message = jobService.deleteJob(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(message)
                        .data(null)
                        .build()
        );
    }
}