package com.avneet.hiretrack.dto;

import lombok.Data;

@Data
public class JobRequest {

    private String title;

    private String company;

    private String location;

    private String jobType;

    private String description;

    private Double salary;
}