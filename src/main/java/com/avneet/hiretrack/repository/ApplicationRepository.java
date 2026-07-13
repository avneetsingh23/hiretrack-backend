package com.avneet.hiretrack.repository;

import com.avneet.hiretrack.entity.Application;
import com.avneet.hiretrack.entity.Job;
import com.avneet.hiretrack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Check if user has already applied
    Optional<Application> findByUserAndJob(User user, Job job);

    // Get all applications of a user
    List<Application> findByUser(User user);

    // Get all applications of a job
    List<Application> findByJob(Job job);
}