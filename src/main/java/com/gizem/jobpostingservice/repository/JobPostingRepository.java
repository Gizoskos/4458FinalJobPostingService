package com.gizem.jobpostingservice.repository;

import com.gizem.jobpostingservice.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobPostingRepository extends MongoRepository<Job, String> {
    List<Job> findByDepartmentIgnoreCase(String department);
   /* @Query("{ '_id': ?0 }")
    Optional<Job> findByStringId(String id);*/
}
