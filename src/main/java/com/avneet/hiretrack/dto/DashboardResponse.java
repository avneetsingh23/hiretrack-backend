package com.avneet.hiretrack.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private long totalJobs;
    private long totalApplications;
    private long shortlisted;
    private long rejected;
}