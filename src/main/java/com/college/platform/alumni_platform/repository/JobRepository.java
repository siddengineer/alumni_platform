// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Job;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface JobRepository extends JpaRepository<Job, Long> {
//     List<Job> findByStatus(Job.JobStatus status);
// }
package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(Job.JobStatus status);

}