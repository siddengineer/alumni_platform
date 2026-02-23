// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/jobs")
// public class StudentController {

//     private final JobRepository jobRepository;

//     public StudentController(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     // ✅ Students see ONLY approved jobs
//     @GetMapping
//     public List<Job> getApprovedJobs() {
//         return jobRepository.findByStatus(Job.JobStatus.APPROVED);
//     }
// }



// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/students")   // changed
// public class StudentController {

//     private final JobRepository jobRepository;

//     public StudentController(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     // Students see ONLY approved jobs
//     @GetMapping("/jobs")   // changed
//     public List<Job> getApprovedJobs() {
//         return jobRepository.findByStatus(Job.JobStatus.APPROVED);
//     }
// }







package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.JobApplication;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.JobApplicationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public StudentController(JobRepository jobRepository,
                             JobApplicationRepository jobApplicationRepository) {
        this.jobRepository = jobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    // Students see ONLY approved jobs
    @GetMapping("/jobs")
    public List<Job> getApprovedJobs() {
        return jobRepository.findByStatus(Job.JobStatus.APPROVED);
    }

    // Student applies for a job
    @PostMapping("/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId,
                              @RequestParam Long studentId) {

        JobApplication application = new JobApplication();
        application.setJobId(jobId);
        application.setStudentId(studentId);

        jobApplicationRepository.save(application);

        return "Application submitted successfully";
    }
}