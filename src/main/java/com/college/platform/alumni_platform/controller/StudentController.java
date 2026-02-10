package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class StudentController {

    private final JobRepository jobRepository;

    public StudentController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // ✅ Students see ONLY approved jobs
    @GetMapping
    public List<Job> getApprovedJobs() {
        return jobRepository.findByStatus(Job.JobStatus.APPROVED);
    }
}