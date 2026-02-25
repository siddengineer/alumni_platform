package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.*;
import com.college.platform.alumni_platform.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student/applications")
public class StudentApplicationController {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public StudentApplicationController(ApplicationRepository applicationRepository,
                                        JobRepository jobRepository,
                                        UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{jobId}")
    public Application apply(@PathVariable Long jobId,
                             @RequestParam String resumeLink) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // 🚨 Prevent duplicate application
        if (applicationRepository.findByStudentAndJob(student, job).isPresent()) {
            throw new RuntimeException("You already applied for this job");
        }

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setResumeLink(resumeLink);

        return applicationRepository.save(application);
    }
}