package com.avneet.hiretrack.controller;

import com.avneet.hiretrack.dto.JobRequest;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public String addJob(@RequestBody JobRequest request) {
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
                            @RequestBody JobRequest request) {

        return jobService.updateJob(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
}