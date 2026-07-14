package com.avneet.hiretrack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank(message = "Job Title is required")
    private String title;

    @NotBlank(message = "Company Name is required")
    private String company;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Job Type is required")
    private String jobType;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Salary is required")
    @Min(value = 1, message = "Salary must be greater than 0")
    private Double salary;
}