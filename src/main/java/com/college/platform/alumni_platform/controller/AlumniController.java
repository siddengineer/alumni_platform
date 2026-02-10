// package com.college.platform.alumni_platform.controller;

// import com.college.platform.alumni_platform.dto.JobRequest;
// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.entity.User;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import com.college.platform.alumni_platform.repository.UserRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/v1/alumni")
// @RequiredArgsConstructor
// public class AlumniController {

//     private final JobRepository jobRepository;
//     private final UserRepository userRepository;

//     @PostMapping("/jobs")
//     public Job createJob(@RequestBody JobRequest request) {

//         User alumni = userRepository.findById(request.getAlumniId())
//                 .orElseThrow(() -> new RuntimeException("Alumni not found"));

//         Job job = new Job();
//         job.setTitle(request.getTitle());
//         job.setDescription(request.getDescription());
//         job.setRequiredSkills(request.getRequiredSkills());
//         job.setDuration(request.getDuration());
//         job.setPaymentAmount(request.getPaymentAmount());
//         job.setStatus(Job.JobStatus.PENDING);
//         job.setAlumni(alumni);

//         return jobRepository.save(job);
//     }
// }
package com.college.platform.alumni_platform.controller;

import com.college.platform.alumni_platform.dto.JobRequest;
import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alumni")
public class AlumniController {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    // ✅ EXPLICIT CONSTRUCTOR
    public AlumniController(
            JobRepository jobRepository,
            UserRepository userRepository
    ) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/jobs")
    public Job createJob(@RequestBody JobRequest request) {

        User alumni = userRepository.findById(request.getAlumniId())
                .orElseThrow(() -> new RuntimeException("Alumni not found"));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setDuration(request.getDuration());
        job.setPaymentAmount(request.getPaymentAmount());
        job.setStatus(Job.JobStatus.PENDING);
        job.setAlumni(alumni);

        return jobRepository.save(job);
    }
}
