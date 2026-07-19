package com.avneet.hiretrack.service;

import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.entity.User;

import java.util.List;

public interface AdminService {

    List<User> getAllUsers();

    List<Job> getAllJobs();

    List<Application> getAllApplications();

    String deleteUser(Long userId);

    String deleteJob(Long jobId);
}