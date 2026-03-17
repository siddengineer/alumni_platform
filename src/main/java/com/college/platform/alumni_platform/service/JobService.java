// package com.college.platform.alumni_platform.service;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class JobService {

//     private final JobRepository jobRepository;

//     public JobService(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     @Cacheable("jobs")
//     public List<Job> getAllJobs() {
//         System.out.println("Fetching from DB...");
//         return jobRepository.findAll();
//     }
// }



// package com.college.platform.alumni_platform.service;

// import com.college.platform.alumni_platform.entity.Job;
// import com.college.platform.alumni_platform.repository.JobRepository;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class JobService {

//     private final JobRepository jobRepository;

//     public JobService(JobRepository jobRepository) {
//         this.jobRepository = jobRepository;
//     }

//     // ✅ CACHE READ
//     @Cacheable("jobs")
//     public List<Job> getAllJobs() {
//         System.out.println("Fetching jobs from DB...");
//         return jobRepository.findAll();
//     }

//     // ✅ ADD JOB + CLEAR CACHE
//     @CacheEvict(value = "jobs", allEntries = true)
//     public Job addJob(Job job) {
//         return jobRepository.save(job);
//     }
// }




package com.college.platform.alumni_platform.service;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Cacheable("jobs")
    public List<Job> getAllJobs() {
        logger.info("🔥 Fetching jobs from DB...");
        return jobRepository.findAll();
    }

    @CacheEvict(value = "jobs", allEntries = true)
    public Job addJob(Job job) {
        logger.info("🧹 Cache cleared (new job added)");
        return jobRepository.save(job);
    }
}