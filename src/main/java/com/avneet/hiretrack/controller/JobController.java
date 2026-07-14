package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.service.JobService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public String addJob(@Valid @RequestBody JobRequest request) {
        return jobService.addJob(request);
    }

    @GetMapping
    public Page<Job> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return jobService.getAllJobs(page, size, sortBy, direction);
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String keyword) {
        return jobService.searchJobs(keyword);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    @PutMapping("/{id}")
    public String updateJob(@PathVariable Long id,
                            @Valid @RequestBody JobRequest request) {

        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
}