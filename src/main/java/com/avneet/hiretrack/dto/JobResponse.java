package com.avneet.hiretrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private Long id;

    private String title;

    private String company;

    private String location;

    private String jobType;

    private String description;

    private Double salary;

    private LocalDateTime createdAt;
}