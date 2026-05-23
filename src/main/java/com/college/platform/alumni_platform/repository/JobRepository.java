// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Job;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface JobRepository extends JpaRepository<Job, Long> {
//     List<Job> findByStatus(Job.JobStatus status);
// }
// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Job;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface JobRepository extends JpaRepository<Job, Long> {

//     List<Job> findByStatus(Job.JobStatus status);

// }


// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Job;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;
// import java.util.Optional;
// public interface JobRepository extends JpaRepository<Job, Long> {

//     List<Job> findByStatus(Job.JobStatus status);
//     Optional<Job> findByRazorpayOrderId(String razorpayOrderId);
// }





package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(Job.JobStatus status);

    Optional<Job> findByRazorpayOrderId(String razorpayOrderId);

    // Needed for alumni to see only their own jobs
    List<Job> findByAlumni(User alumni);

    // Needed for admin stats
    long countByStatus(Job.JobStatus status);
}