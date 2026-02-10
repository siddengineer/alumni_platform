package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
