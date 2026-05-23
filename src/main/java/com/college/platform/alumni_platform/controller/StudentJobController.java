// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/students/jobs")
// public class StudentJobController {

//     private final JobRepository jobRepository;

//     public StudentJobController(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     @GetMapping
//     public List<Job> getApprovedJobs() {
//         return jobRepository.findByStatus(Job.JobStatus.APPROVED);
//     }
// }


// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Application;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.ApplicationRepository;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import com.college.platform.alumni_platform.service.JobService;

// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/student/jobs")
// public class StudentJobController {

//     private final JobRepository jobRepository;
//     private final ApplicationRepository applicationRepository;
//     private final UserRepository userRepository;

//     public StudentJobController(
//             JobRepository jobRepository,
//             ApplicationRepository applicationRepository,
//             UserRepository userRepository
//     ) {
//         this.jobRepository = jobRepository;
//         this.applicationRepository = applicationRepository;
//         this.userRepository = userRepository;
//     }

//     // ✅ View Approved Jobs
//     @GetMapping
//     public List<Job> getApprovedJobs() {
//         return jobRepository.findByStatus(Job.JobStatus.APPROVED);
//     }

//         @GetMapping
//         public List<Job> getJobs() {
//             return jobService.getAllJobs();
//     }   
// }
//     // ✅ Apply For Job
//     @PostMapping("/{jobId}/apply")
//     public String applyForJob(@PathVariable Long jobId,
//                               Authentication authentication) {

//         // Get logged-in student email
//         String email = authentication.getName();

//         User student = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("Student not found"));

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         // Check job approved
//         if (job.getStatus() != Job.JobStatus.APPROVED) {
//             throw new RuntimeException("Job not approved yet");
//         }

//         // Check duplicate apply
//         if (applicationRepository.findByStudentAndJob(student, job).isPresent()) {
//             throw new RuntimeException("You already applied for this job");
//         }

//         // Save application
//         Application application = new Application();
//         application.setStudent(student);
//         application.setJob(job);

//         applicationRepository.save(application);

//         return "Applied successfully";
//     }
// }








// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Application;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.ApplicationRepository;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import com.college.platform.alumni_platform.service.JobService;

// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/student/jobs")
// public class StudentJobController {

//     private final JobService jobService;
//     private final JobRepository jobRepository;
//     private final ApplicationRepository applicationRepository;
//     private final UserRepository userRepository;

//     public StudentJobController(
//             JobService jobService,
//             JobRepository jobRepository,
//             ApplicationRepository applicationRepository,
//             UserRepository userRepository
//     ) {
//         this.jobService = jobService;
//         this.jobRepository = jobRepository;
//         this.applicationRepository = applicationRepository;
//         this.userRepository = userRepository;
//     }

//     // ✅ View Approved Jobs (WITH CACHE via service)
//     @GetMapping
//     public List<Job> getJobs() {
//         return jobService.getAllJobs();
//     }

//     // ✅ Apply For Job
//     @PostMapping("/{jobId}/apply")
//     public String applyForJob(@PathVariable Long jobId,
//                              Authentication authentication) {

//         // Get logged-in student email
//         String email = authentication.getName();

//         User student = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("Student not found"));

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         // Check job approved
//         if (job.getStatus() != Job.JobStatus.APPROVED) {
//             throw new RuntimeException("Job not approved yet");
//         }

//         // Check duplicate apply
//         if (applicationRepository.findByStudentAndJob(student, job).isPresent()) {
//             throw new RuntimeException("You already applied for this job");
//         }

//         // Save application
//         Application application = new Application();
//         application.setStudent(student);
//         application.setJob(job);

//         applicationRepository.save(application);

//         return "Applied successfully";
//     }
// }










package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Application;
import com.college.platform.alumni_platform.entity.Certificate;
import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.ApplicationRepository;
import com.college.platform.alumni_platform.repository.CertificateRepository;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentJobController {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final CertificateRepository certificateRepository;

    public StudentJobController(JobRepository jobRepository,
                                ApplicationRepository applicationRepository,
                                UserRepository userRepository,
                                CertificateRepository certificateRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.certificateRepository = certificateRepository;
    }

    // ── VIEW APPROVED JOBS ONLY ──
    @GetMapping("/jobs")
    public List<Job> getApprovedJobs() {
        return jobRepository.findByStatus(Job.JobStatus.APPROVED);
    }

    // ── APPLY FOR A JOB ──
    @PostMapping("/applications/{jobId}")
    public ResponseEntity<?> apply(@PathVariable Long jobId,
                                   @RequestParam String resumeLink,
                                   Authentication authentication) {
        String email = authentication.getName();

        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (job.getStatus() != Job.JobStatus.APPROVED) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Job is not open for applications"));
        }

        if (applicationRepository.findByStudentAndJob(student, job).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "You already applied for this job"));
        }

        Application application = new Application();
        application.setStudent(student);
        application.setJob(job);
        application.setResumeLink(resumeLink);
        applicationRepository.save(application);

        return ResponseEntity.ok(application);
    }

    // ── GET MY APPLICATIONS ──
    @GetMapping("/applications")
    public ResponseEntity<?> getMyApplications(Authentication authentication) {
        String email = authentication.getName();
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return ResponseEntity.ok(applicationRepository.findByStudent(student));
    }

    // ── GET MY CERTIFICATES ──
    @GetMapping("/certificates")
    public ResponseEntity<?> getMyCertificates(Authentication authentication) {
        String email = authentication.getName();
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        List<Certificate> certs = certificateRepository.findByStudent(student);
        return ResponseEntity.ok(certs);
    }
}