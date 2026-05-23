// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.Payment;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.PaymentRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/v1/admin")
// @RequiredArgsConstructor
// public class AdminController {

//     private final JobRepository jobRepository;
//     private final PaymentRepository paymentRepository;

//     @PutMapping("/jobs/{jobId}/approve")
//     public String approveJob(@PathVariable Long jobId) {

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         job.setStatus(Job.JobStatus.APPROVED);
//         jobRepository.save(job);

//         Payment payment = new Payment();
//         payment.setJob(job);
//         payment.setAmount(job.getPaymentAmount());
//         payment.setStatus(Payment.PaymentStatus.HELD);

//         paymentRepository.save(payment);

//         return "Job approved and payment held";
//     }
// }
// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.Payment;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.PaymentRepository;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/v1/admin")
// public class AdminController {

//     private final JobRepository jobRepository;
//     private final PaymentRepository paymentRepository;

//     // ✅ EXPLICIT CONSTRUCTOR (MANDATORY)
//     public AdminController(
//             JobRepository jobRepository,
//             PaymentRepository paymentRepository
//     ) {
//         this.jobRepository = jobRepository;
//         this.paymentRepository = paymentRepository;
//     }

//     @PutMapping("/jobs/{jobId}/approve")
//     public String approveJob(@PathVariable Long jobId) {

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         job.setStatus(Job.JobStatus.APPROVED);
//         jobRepository.save(job);

//         Payment payment = new Payment();
//         payment.setAmount(job.getPaymentAmount());
//         payment.setStatus(Payment.PaymentStatus.HELD);
//         payment.setJob(job);

//         paymentRepository.save(payment);

//         return "Job approved and payment held";
//     }
// }





// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.Payment;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.PaymentRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/admin")
// public class AdminController {

//     private final JobRepository jobRepository;
//     private final PaymentRepository paymentRepository;

//     public AdminController(
//             JobRepository jobRepository,
//             PaymentRepository paymentRepository
//     ) {
//         this.jobRepository = jobRepository;
//         this.paymentRepository = paymentRepository;
//     }

//     // ✅ GET PENDING JOBS
//     @GetMapping("/jobs/pending")
//     public List<Job> getPendingJobs() {
//         return jobRepository.findByStatus(Job.JobStatus.PENDING);
//     }

//     // ✅ APPROVE JOB
//     @PutMapping("/jobs/{jobId}/approve")
//     public String approveJob(@PathVariable Long jobId) {

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         job.setStatus(Job.JobStatus.APPROVED);
//         jobRepository.save(job);

//         Payment payment = new Payment();
//         payment.setAmount(job.getPaymentAmount());
//         payment.setStatus(Payment.PaymentStatus.HELD);
//         payment.setJob(job);

//         paymentRepository.save(payment);

//         return "Job approved and payment held";
//     }
// }










// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Certificate;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.Payment;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.ApplicationRepository;
// import com.college.platform.alumni_platform.repository.CertificateRepository;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.PaymentLogRepository;
// import com.college.platform.alumni_platform.repository.PaymentRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/admin")
// public class AdminController {

//     private final JobRepository jobRepository;
//     private final PaymentRepository paymentRepository;
//     private final UserRepository userRepository;
//     private final ApplicationRepository applicationRepository;
//     private final CertificateRepository certificateRepository;
//     private final PaymentLogRepository paymentLogRepository;

//     public AdminController(JobRepository jobRepository,
//                            PaymentRepository paymentRepository,
//                            UserRepository userRepository,
//                            ApplicationRepository applicationRepository,
//                            CertificateRepository certificateRepository,
//                            PaymentLogRepository paymentLogRepository) {
//         this.jobRepository = jobRepository;
//         this.paymentRepository = paymentRepository;
//         this.userRepository = userRepository;
//         this.applicationRepository = applicationRepository;
//         this.certificateRepository = certificateRepository;
//         this.paymentLogRepository = paymentLogRepository;
//     }

//     // ── OVERVIEW STATS ──
//     @GetMapping("/stats")
//     public Map<String, Object> getStats() {
//         Map<String, Object> stats = new HashMap<>();
//         stats.put("totalStudents", userRepository.countByRole("STUDENT"));
//         stats.put("totalAlumni",   userRepository.countByRole("ALUMNI"));
//         stats.put("totalJobs",     jobRepository.count());
//         stats.put("pendingJobs",   jobRepository.countByStatus(Job.JobStatus.PENDING));
//         stats.put("approvedJobs",  jobRepository.countByStatus(Job.JobStatus.APPROVED));
//         stats.put("completedJobs", jobRepository.countByStatus(Job.JobStatus.COMPLETED));
//         stats.put("totalApplications", applicationRepository.count());
//         stats.put("totalPayments", paymentRepository.count());
//         return stats;
//     }

//     // ── GET ALL USERS ──
//     @GetMapping("/users")
//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     // ── GET USERS BY ROLE ──
//     @GetMapping("/users/role/{role}")
//     public List<User> getUsersByRole(@PathVariable String role) {
//         return userRepository.findByRole(role.toUpperCase());
//     }

//     // ── GET PENDING JOBS ──
//     @GetMapping("/jobs/pending")
//     public List<Job> getPendingJobs() {
//         return jobRepository.findByStatus(Job.JobStatus.PENDING);
//     }

//     // ── GET ALL JOBS ──
//     @GetMapping("/jobs")
//     public List<Job> getAllJobs() {
//         return jobRepository.findAll();
//     }

//     // ── APPROVE JOB ──
//     @PutMapping("/jobs/{jobId}/approve")
//     public ResponseEntity<?> approveJob(@PathVariable Long jobId) {
//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         job.setStatus(Job.JobStatus.APPROVED);
//         jobRepository.save(job);

//         Payment payment = new Payment();
//         payment.setAmount(job.getPaymentAmount());
//         payment.setStatus(Payment.PaymentStatus.HELD);
//         payment.setJob(job);
//         paymentRepository.save(payment);

//         return ResponseEntity.ok(Map.of("message", "Job approved and payment held"));
//     }

//     // ── REJECT JOB ──
//     @PutMapping("/jobs/{jobId}/reject")
//     public ResponseEntity<?> rejectJob(@PathVariable Long jobId) {
//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));
//         job.setStatus(Job.JobStatus.REJECTED);
//         jobRepository.save(job);
//         return ResponseEntity.ok(Map.of("message", "Job rejected"));
//     }

//     // ── ISSUE CERTIFICATE ──
//     @PostMapping("/jobs/{jobId}/certificate")
//     public ResponseEntity<?> issueCertificate(@PathVariable Long jobId,
//                                                @RequestBody Map<String, String> body) {
//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         Long studentId = Long.parseLong(body.get("studentId"));
//         String certName = body.getOrDefault("certificateName", "Job Completion Certificate");

//         User student = userRepository.findById(studentId)
//                 .orElseThrow(() -> new RuntimeException("Student not found"));

//         Certificate cert = new Certificate();
//         cert.setCertificateName(certName);
//         cert.setStudent(student);
//         cert.setJob(job);
//         certificateRepository.save(cert);

//         job.setStatus(Job.JobStatus.COMPLETED);
//         jobRepository.save(job);

//         return ResponseEntity.ok(Map.of("message", "Certificate issued to " + student.getEmail()));
//     }

//     // ── GET ALL PAYMENT LOGS ──
//     @GetMapping("/payments")
//     public ResponseEntity<?> getPaymentLogs() {
//         return ResponseEntity.ok(paymentLogRepository.findAll());
//     }
// }








package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Certificate;
import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.Payment;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.ApplicationRepository;
import com.college.platform.alumni_platform.repository.CertificateRepository;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.PaymentLogRepository;
import com.college.platform.alumni_platform.repository.PaymentRepository;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final JobRepository jobRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final CertificateRepository certificateRepository;
    private final PaymentLogRepository paymentLogRepository;

    public AdminController(JobRepository jobRepository,
                           PaymentRepository paymentRepository,
                           UserRepository userRepository,
                           ApplicationRepository applicationRepository,
                           CertificateRepository certificateRepository,
                           PaymentLogRepository paymentLogRepository) {
        this.jobRepository = jobRepository;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.certificateRepository = certificateRepository;
        this.paymentLogRepository = paymentLogRepository;
    }

    // ── OVERVIEW STATS ──
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalStudents",     userRepository.countByRole("STUDENT"));
        stats.put("totalAlumni",       userRepository.countByRole("ALUMNI"));
        stats.put("totalJobs",         jobRepository.count());
        stats.put("pendingJobs",       jobRepository.countByStatus(Job.JobStatus.PENDING));
        stats.put("approvedJobs",      jobRepository.countByStatus(Job.JobStatus.APPROVED));
        stats.put("completedJobs",     jobRepository.countByStatus(Job.JobStatus.COMPLETED));
        stats.put("totalApplications", applicationRepository.count());
        stats.put("totalPayments",     paymentRepository.count());
        return stats;
    }

    // ── GET ALL USERS ──
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ── GET USERS BY ROLE ──
    @GetMapping("/users/role/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return userRepository.findByRole(role.toUpperCase());
    }

    // ── GET PENDING JOBS ──
    @GetMapping("/jobs/pending")
    public List<Job> getPendingJobs() {
        return jobRepository.findByStatus(Job.JobStatus.PENDING);
    }

    // ── GET ALL JOBS ──
    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // ── APPROVE JOB ──
    // BUG FIX 1: Was creating a duplicate Payment row every time approve was clicked.
    //            Now checks if a Payment already exists for this job before creating one.
    // BUG FIX 2: Was not evicting the jobs/approvedJobs caches after status change,
    //            so students would keep seeing the stale PENDING state.
    @PutMapping("/jobs/{jobId}/approve")
    @CacheEvict(value = {"jobs", "approvedJobs"}, allEntries = true)
    public ResponseEntity<?> approveJob(@PathVariable Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (job.getStatus() == Job.JobStatus.APPROVED) {
            return ResponseEntity.ok(Map.of("message", "Job already approved"));
        }

        job.setStatus(Job.JobStatus.APPROVED);
        jobRepository.save(job);

        // Only create Payment if one doesn't already exist for this job
        Optional<Payment> existing = paymentRepository.findByJob(job);
        if (existing.isEmpty()) {
            Payment payment = new Payment();
            payment.setAmount(job.getPaymentAmount());
            payment.setStatus(Payment.PaymentStatus.HELD);
            payment.setJob(job);
            paymentRepository.save(payment);
        }

        return ResponseEntity.ok(Map.of("message", "Job approved and payment held"));
    }

    // ── REJECT JOB ──
    // BUG FIX: Was not evicting caches after status change.
    @PutMapping("/jobs/{jobId}/reject")
    @CacheEvict(value = {"jobs", "approvedJobs"}, allEntries = true)
    public ResponseEntity<?> rejectJob(@PathVariable Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setStatus(Job.JobStatus.REJECTED);
        jobRepository.save(job);
        return ResponseEntity.ok(Map.of("message", "Job rejected"));
    }

    // ── ISSUE CERTIFICATE ──
    // BUG FIX: Was not evicting the certificates cache, so students would never
    //          see newly issued certificates (always got the stale empty list).
    // BUG FIX: Was not checking for duplicate certificate issuance.
    @PostMapping("/jobs/{jobId}/certificate")
    @CacheEvict(value = "certificates", allEntries = true)
    public ResponseEntity<?> issueCertificate(@PathVariable Long jobId,
                                               @RequestBody Map<String, String> body) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Long studentId = Long.parseLong(body.get("studentId"));
        String certName = body.getOrDefault("certificateName", "Job Completion Certificate");

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Prevent duplicate certificate for the same student+job
        boolean alreadyIssued = certificateRepository.findByJob_Id(jobId)
                .stream()
                .anyMatch(c -> c.getStudent().getId().equals(studentId));

        if (alreadyIssued) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Certificate already issued to this student for this job"));
        }

        Certificate cert = new Certificate();
        cert.setCertificateName(certName);
        cert.setStudent(student);
        cert.setJob(job);
        certificateRepository.save(cert);

        job.setStatus(Job.JobStatus.COMPLETED);
        jobRepository.save(job);

        return ResponseEntity.ok(Map.of("message", "Certificate issued to " + student.getEmail()));
    }

    // ── GET ALL PAYMENT LOGS ──
    @GetMapping("/payments")
    public ResponseEntity<?> getPaymentLogs() {
        return ResponseEntity.ok(paymentLogRepository.findAll());
    }
}