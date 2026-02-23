// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// public class JobApplication {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long jobId;
//     private Long studentId;

//     @Enumerated(EnumType.STRING)
//     private ApplicationStatus status;

//     private LocalDateTime appliedAt;

//     public enum ApplicationStatus {
//         APPLIED,
//         ACCEPTED,
//         REJECTED
//     }

//     public JobApplication() {
//         this.appliedAt = LocalDateTime.now();
//         this.status = ApplicationStatus.APPLIED;
//     }

//     // getters and setters
// }




package com.college.platform.alumni_platform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private Long studentId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime appliedAt;

    public enum ApplicationStatus {
        APPLIED,
        ACCEPTED,
        REJECTED
    }

    public JobApplication() {
        this.appliedAt = LocalDateTime.now();
        this.status = ApplicationStatus.APPLIED;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}