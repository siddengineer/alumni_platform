package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // ✅ Students can view APPROVED jobs
    @GetMapping
    public List<Job> getApprovedJobs() {
        return jobRepository.findByStatus(Job.JobStatus.APPROVED);
    }
}