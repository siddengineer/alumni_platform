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





package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.Payment;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final JobRepository jobRepository;
    private final PaymentRepository paymentRepository;

    public AdminController(
            JobRepository jobRepository,
            PaymentRepository paymentRepository
    ) {
        this.jobRepository = jobRepository;
        this.paymentRepository = paymentRepository;
    }

    // ✅ GET PENDING JOBS
    @GetMapping("/jobs/pending")
    public List<Job> getPendingJobs() {
        return jobRepository.findByStatus(Job.JobStatus.PENDING);
    }

    // ✅ APPROVE JOB
    @PutMapping("/jobs/{jobId}/approve")
    public String approveJob(@PathVariable Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setStatus(Job.JobStatus.APPROVED);
        jobRepository.save(job);

        Payment payment = new Payment();
        payment.setAmount(job.getPaymentAmount());
        payment.setStatus(Payment.PaymentStatus.HELD);
        payment.setJob(job);

        paymentRepository.save(payment);

        return "Job approved and payment held";
    }
}