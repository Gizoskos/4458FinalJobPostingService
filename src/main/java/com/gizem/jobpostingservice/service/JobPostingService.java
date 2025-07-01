package com.gizem.jobpostingservice.service;

import com.gizem.jobpostingservice.entity.Job;

import java.util.List;

public interface JobPostingService {
    Job getJobById(String id);
    List<Job> getRelatedJobs(String department);
    void apply(String id);

}
