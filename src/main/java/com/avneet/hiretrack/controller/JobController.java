package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public String addJob(@Valid @RequestBody JobRequest request) {
        return jobService.addJob(request);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
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