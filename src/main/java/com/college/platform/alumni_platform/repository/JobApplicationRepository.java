package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStudentId(Long studentId);

    List<JobApplication> findByJobId(Long jobId);
}


