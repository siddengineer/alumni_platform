// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/jobs")
// public class JobController {

//     private final JobRepository jobRepository;

//     public JobController(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     // ✅ Students can view APPROVED jobs
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
// @RequestMapping("/api/v1/alumni/jobs")   // 🔥 IMPORTANT CHANGE
// public class AlumniJobController {

//     private final JobRepository jobRepository;

//     public AlumniJobController(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     // ✅ ALUMNI can create job
//     @PostMapping
//     public Job createJob(@RequestBody Job job) {
//         job.setStatus(Job.JobStatus.PENDING);
//         return jobRepository.save(job);
//     }

//     // (Optional) Alumni can see their posted jobs
//     @GetMapping
//     public List<Job> getAllJobs() {
//         return jobRepository.findAll();
//     }
// }


// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/alumni/jobs")
// public class AlumniJobController {

//     private final JobRepository jobRepository;
//     private final UserRepository userRepository;

//     public AlumniJobController(JobRepository jobRepository,
//                                UserRepository userRepository) {
//         this.jobRepository = jobRepository;
//         this.userRepository = userRepository;
//     }

//     // ✅ Create Job (Linked to logged-in Alumni)
//     @PostMapping
//     public Job createJob(@RequestBody Job job) {

//         Authentication authentication = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication();

//         if (authentication == null || !authentication.isAuthenticated()) {
//             throw new RuntimeException("User not authenticated");
//         }

//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         job.setAlumni(alumni);
//         job.setStatus(Job.JobStatus.PENDING);

//         return jobRepository.save(job);
//     }

//     // ✅ View all jobs (optional)
//     @GetMapping
//     public List<Job> getAllJobs() {
//         return jobRepository.findAll();
//     }
// }




// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Application;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.ApplicationRepository;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/alumni/jobs")
// public class AlumniJobController {

//     private final JobRepository jobRepository;
//     private final UserRepository userRepository;
//     private final ApplicationRepository applicationRepository;

//     public AlumniJobController(JobRepository jobRepository,
//                                UserRepository userRepository,
//                                ApplicationRepository applicationRepository) {
//         this.jobRepository = jobRepository;
//         this.userRepository = userRepository;
//         this.applicationRepository = applicationRepository;
//     }

//     // ✅ Create Job (Linked to logged-in Alumni)
//     @PostMapping
//     public Job createJob(@RequestBody Job job) {

//         Authentication authentication = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication();

//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         job.setAlumni(alumni);
//         job.setStatus(Job.JobStatus.PENDING);

//         return jobRepository.save(job);
//     }

//     // ✅ View All Jobs (Optional)
//     @GetMapping
//     public List<Job> getAllJobs() {
//         return jobRepository.findAll();
//     }

//     // ✅ View Applications For Specific Job
//     @GetMapping("/{jobId}/applications")
//     public List<Application> viewApplications(@PathVariable Long jobId) {

//         Authentication authentication = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication();

//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         // Security check: ensure job belongs to this alumni
//         if (job.getAlumni() == null ||
//             !job.getAlumni().getId().equals(alumni.getId())) {
//             throw new RuntimeException("Not authorized to view applications");
//         }

//         return applicationRepository.findByJob(job);
//     }
// }



// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.entity.Application;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.ApplicationRepository;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/alumni/jobs")
// public class AlumniJobController {

//     private final JobRepository jobRepository;
//     private final UserRepository userRepository;
//     private final ApplicationRepository applicationRepository;

//     public AlumniJobController(JobRepository jobRepository,
//                                UserRepository userRepository,
//                                ApplicationRepository applicationRepository) {
//         this.jobRepository = jobRepository;
//         this.userRepository = userRepository;
//         this.applicationRepository = applicationRepository;
//     }

//     // ✅ Create Job (Linked to logged-in Alumni)
//     @PostMapping
//     public Job createJob(@RequestBody Job job) {

//         Authentication authentication = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication();

//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         job.setAlumni(alumni);
//         job.setStatus(Job.JobStatus.PENDING);
//         job.setPaymentStatus(Job.PaymentStatus.HELD);

//         return jobRepository.save(job);
//     }

//     // ✅ View All Jobs
//     @GetMapping
//     public List<Job> getAllJobs() {
//         return jobRepository.findAll();
//     }

//     // ✅ View Applications For Specific Job
//     @GetMapping("/{jobId}/applications")
//     public List<Application> viewApplications(@PathVariable Long jobId) {

//         Authentication authentication = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication();

//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         // Security check
//         if (job.getAlumni() == null ||
//             !job.getAlumni().getId().equals(alumni.getId())) {
//             throw new RuntimeException("Not authorized to view applications");
//         }

//         return applicationRepository.findByJob(job);
//     }

//     // ✅ SELECT STUDENT (Final Hiring Logic)
//     @PostMapping("/applications/{applicationId}/select")
//     @Transactional
//     public String selectStudent(@PathVariable Long applicationId) {

//         Authentication authentication = SecurityContextHolder
//                 .getContext()
//                 .getAuthentication();

//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Application selectedApp = applicationRepository.findById(applicationId)
//                 .orElseThrow(() -> new RuntimeException("Application not found"));

//         Job job = selectedApp.getJob();

//         // 🔐 Security Check
//         if (job.getAlumni() == null ||
//             !job.getAlumni().getId().equals(alumni.getId())) {
//             throw new RuntimeException("Not authorized to select for this job");
//         }

//         // 1️⃣ Mark selected
//         selectedApp.setStatus(Application.ApplicationStatus.SELECTED);

//         // 2️⃣ Reject others
//         List<Application> allApps = applicationRepository.findByJob(job);

//         for (Application app : allApps) {
//             if (!app.getId().equals(applicationId)) {
//                 app.setStatus(Application.ApplicationStatus.REJECTED);
//             }
//         }

//         // 3️⃣ Update job
//         job.setPaymentStatus(Job.PaymentStatus.RELEASED);
//         job.setStatus(Job.JobStatus.ASSIGNED);

//         return "Student selected and payment released successfully";
//     }
// }








// package com.college.platform.alumni_platform.controller;
// import com.college.platform.alumni_platform.entity.PaymentLog;
// import com.college.platform.alumni_platform.repository.PaymentLogRepository;
// import com.college.platform.alumni_platform.entity.Application;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.ApplicationRepository;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import com.college.platform.alumni_platform.service.RazorpayService;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/alumni/jobs")
// public class AlumniJobController {

//     private final JobRepository jobRepository;
//     private final UserRepository userRepository;
//     private final ApplicationRepository applicationRepository;
//     private final RazorpayService razorpayService;

//     @Value("${razorpay.key}")
//     private String razorpayKey;

//     @Value("${razorpay.secret}")
//     private String razorpaySecret;

//     public AlumniJobController(JobRepository jobRepository,
//                                UserRepository userRepository,
//                                ApplicationRepository applicationRepository,
//                                RazorpayService razorpayService) {
//         this.jobRepository = jobRepository;
//         this.userRepository = userRepository;
//         this.applicationRepository = applicationRepository;
//         this.razorpayService = razorpayService;
//     }

//     // ✅ Create Job
//     @PostMapping
//     public Job createJob(@RequestBody Job job) {

//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         job.setAlumni(alumni);
//         job.setStatus(Job.JobStatus.PENDING);
//         job.setPaymentStatus(Job.PaymentStatus.HELD);

//         return jobRepository.save(job);
//     }

//     // ✅ View All Jobs
//     @GetMapping
//     public List<Job> getAllJobs() {
//         return jobRepository.findAll();
//     }

//     // ✅ View Applications For Job
//     @GetMapping("/{jobId}/applications")
//     public List<Application> viewApplications(@PathVariable Long jobId) {

//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Job job = jobRepository.findById(jobId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         if (job.getAlumni() == null ||
//                 !job.getAlumni().getId().equals(alumni.getId())) {
//             throw new RuntimeException("Not authorized");
//         }

//         return applicationRepository.findByJob(job);
//     }

//     // ✅ SELECT STUDENT → CREATE RAZORPAY ORDER
//     @PostMapping("/applications/{applicationId}/select")
//     @Transactional
//     public Map<String, Object> selectStudent(@PathVariable Long applicationId) throws Exception {

//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         String email = authentication.getName();

//         User alumni = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         Application selectedApp = applicationRepository.findById(applicationId)
//                 .orElseThrow(() -> new RuntimeException("Application not found"));

//         Job job = selectedApp.getJob();

//         // 🔐 Security Check
//         if (job.getAlumni() == null ||
//                 !job.getAlumni().getId().equals(alumni.getId())) {
//             throw new RuntimeException("Not authorized");
//         }

//         // 1️⃣ Select student
//         selectedApp.setStatus(Application.ApplicationStatus.SELECTED);
//         job.setStatus(Job.JobStatus.ASSIGNED);

//         // 2️⃣ Reject others
//         List<Application> allApps = applicationRepository.findByJob(job);
//         for (Application app : allApps) {
//             if (!app.getId().equals(applicationId)) {
//                 app.setStatus(Application.ApplicationStatus.REJECTED);
//             }
//         }

//         // 3️⃣ Create Razorpay Order
//         var order = razorpayService.createOrder(job.getPaymentAmount());

//         // 4️⃣ Save orderId in Job
//         job.setRazorpayOrderId(order.get("id").toString());

//         return Map.of(
//                 "orderId", order.get("id"),
//                 "amount", order.get("amount"),
//                 "currency", order.get("currency"),
//                 "key", razorpayKey
//         );
//     }

//     // ✅ VERIFY PAYMENT
//     @PostMapping("/payment/verify")
//     @Transactional
//     public String verifyPayment(@RequestBody Map<String, String> payload) throws Exception {

//         String razorpayOrderId = payload.get("razorpay_order_id");
//         String razorpayPaymentId = payload.get("razorpay_payment_id");
//         String razorpaySignature = payload.get("razorpay_signature");

//         String generatedSignature =
//                 razorpayService.generateSignature(razorpayOrderId, razorpayPaymentId);

//         if (!generatedSignature.equals(razorpaySignature)) {
//             throw new RuntimeException("Invalid payment signature");
//         }

//         Job job = jobRepository.findByRazorpayOrderId(razorpayOrderId)
//                 .orElseThrow(() -> new RuntimeException("Job not found"));

//         job.setPaymentStatus(Job.PaymentStatus.RELEASED);

//         return "Payment verified & released successfully";
//     }
// }











package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.entity.Application;
import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.PaymentLog;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.ApplicationRepository;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.PaymentLogRepository;
import com.college.platform.alumni_platform.repository.UserRepository;
import com.college.platform.alumni_platform.service.RazorpayService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/alumni/jobs")
public class AlumniJobController {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final RazorpayService razorpayService;
    private final PaymentLogRepository paymentLogRepository;

    @Value("${razorpay.key}")
    private String razorpayKey;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    public AlumniJobController(JobRepository jobRepository,
                               UserRepository userRepository,
                               ApplicationRepository applicationRepository,
                               RazorpayService razorpayService,
                               PaymentLogRepository paymentLogRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
        this.razorpayService = razorpayService;
        this.paymentLogRepository = paymentLogRepository;
    }

    // ✅ CREATE JOB
    @PostMapping
    public Job createJob(@RequestBody Job job) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User alumni = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        job.setAlumni(alumni);
        job.setStatus(Job.JobStatus.PENDING);
        job.setPaymentStatus(Job.PaymentStatus.HELD);

        return jobRepository.save(job);
    }

    // ✅ VIEW ALL JOBS
    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // ✅ VIEW APPLICATIONS FOR JOB
    @GetMapping("/{jobId}/applications")
    public List<Application> viewApplications(@PathVariable Long jobId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User alumni = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (job.getAlumni() == null ||
                !job.getAlumni().getId().equals(alumni.getId())) {
            throw new RuntimeException("Not authorized");
        }

        return applicationRepository.findByJob(job);
    }

    // ✅ SELECT STUDENT → CREATE RAZORPAY ORDER
    @PostMapping("/applications/{applicationId}/select")
    @Transactional
    public Map<String, Object> selectStudent(@PathVariable Long applicationId) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User alumni = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application selectedApp = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Job job = selectedApp.getJob();

        // 🔐 SECURITY CHECK
        if (job.getAlumni() == null ||
                !job.getAlumni().getId().equals(alumni.getId())) {
            throw new RuntimeException("Not authorized");
        }

        // 1️⃣ SELECT STUDENT
        selectedApp.setStatus(Application.ApplicationStatus.SELECTED);
        job.setStatus(Job.JobStatus.ASSIGNED);

        // 2️⃣ REJECT OTHER APPLICATIONS
        List<Application> allApps = applicationRepository.findByJob(job);
        for (Application app : allApps) {
            if (!app.getId().equals(applicationId)) {
                app.setStatus(Application.ApplicationStatus.REJECTED);
            }
        }

        // 3️⃣ CREATE RAZORPAY ORDER
        var order = razorpayService.createOrder(job.getPaymentAmount());

        // 4️⃣ SAVE ORDER ID
        job.setRazorpayOrderId(order.get("id").toString());

        return Map.of(
                "orderId", order.get("id"),
                "amount", order.get("amount"),
                "currency", order.get("currency"),
                "key", razorpayKey
        );
    }

    // ✅ VERIFY PAYMENT + AUDIT LOGGING
    @PostMapping("/payment/verify")
    @Transactional
    public String verifyPayment(@RequestBody Map<String, String> payload) throws Exception {

        String razorpayOrderId = payload.get("razorpay_order_id");
        String razorpayPaymentId = payload.get("razorpay_payment_id");
        String razorpaySignature = payload.get("razorpay_signature");

        // 🧾 CREATE LOG ENTRY
        PaymentLog log = new PaymentLog();
        log.setRazorpayOrderId(razorpayOrderId);
        log.setRazorpayPaymentId(razorpayPaymentId);
        log.setSignature(razorpaySignature);

        String generatedSignature =
                razorpayService.generateSignature(razorpayOrderId, razorpayPaymentId);

        // ❌ INVALID SIGNATURE
        if (!generatedSignature.equals(razorpaySignature)) {

            log.setStatus("INVALID_SIGNATURE");
            paymentLogRepository.save(log);

            throw new RuntimeException("Invalid payment signature");
        }

        // 🔎 FIND JOB
        Job job = jobRepository.findByRazorpayOrderId(razorpayOrderId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // 💰 RELEASE PAYMENT
        job.setPaymentStatus(Job.PaymentStatus.RELEASED);

        // ✅ SUCCESS LOG
        log.setStatus("SUCCESS");
        paymentLogRepository.save(log);

        return "Payment verified & released successfully";
    }
}