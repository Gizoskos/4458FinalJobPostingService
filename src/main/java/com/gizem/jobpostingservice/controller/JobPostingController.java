package com.gizem.jobpostingservice.controller;

import com.gizem.jobpostingservice.entity.Job;
import com.gizem.jobpostingservice.service.JobPostingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-posting")
public class JobPostingController {

    private final JobPostingService service;

    public JobPostingController(JobPostingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable String id) {
        return ResponseEntity.ok(service.getJobById(id));
    }

    @PostMapping("/apply")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> apply(@RequestParam String id) {
        service.apply(id);
        return ResponseEntity.ok("Applied");
    }

    @GetMapping("/related")
    public ResponseEntity<List<Job>> related(@RequestParam String department) {
        return ResponseEntity.ok(service.getRelatedJobs(department));
    }
}
