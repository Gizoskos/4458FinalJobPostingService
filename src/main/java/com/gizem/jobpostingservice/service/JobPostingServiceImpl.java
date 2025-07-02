package com.gizem.jobpostingservice.service;

import com.gizem.jobpostingservice.entity.Job;
import com.gizem.jobpostingservice.repository.JobPostingRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository repository;

    public JobPostingServiceImpl(JobPostingRepository repository) {
        this.repository = repository;
    }


    @Override
    /*@Cacheable(value = "jobCache", key = "#id")*/

    public Job getJobById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found"));
    }



    @Override
    public List<Job> getRelatedJobs(String department) {
        return repository.findByDepartmentIgnoreCase(department);
    }

    @Override
    public void apply(String id) {
        Job job = getJobById(id);
        job.setApplicationCount(job.getApplicationCount() + 1);
        repository.save(job);
    }
}
