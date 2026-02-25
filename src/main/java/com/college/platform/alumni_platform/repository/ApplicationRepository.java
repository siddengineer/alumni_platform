package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Application;
import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Prevent duplicate apply
    Optional<Application> findByStudentAndJob(User student, Job job);

    // Alumni can see applications for their job
    List<Application> findByJob(Job job);

    // Student can see their own applications
    List<Application> findByStudent(User student);
}