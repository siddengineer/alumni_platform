// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "applications")
// public class Application {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "student_id")
//     private User student;

//     @ManyToOne
//     @JoinColumn(name = "job_id")
//     private Job job;

//     private String resumeLink;

//     // ===== GETTERS & SETTERS =====

//     public Long getId() {
//         return id;
//     }

//     public User getStudent() {
//         return student;
//     }

//     public void setStudent(User student) {
//         this.student = student;
//     }

//     public Job getJob() {
//         return job;
//     }

//     public void setJob(Job job) {
//         this.job = job;
//     }

//     public String getResumeLink() {
//         return resumeLink;
//     }

//     public void setResumeLink(String resumeLink) {
//         this.resumeLink = resumeLink;
//     }
// }


// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "applications")
// public class Application {

//     public enum ApplicationStatus {
//         APPLIED,
//         SELECTED,
//         REJECTED
//     }

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "student_id")
//     private User student;

//     @ManyToOne
//     @JoinColumn(name = "job_id")
//     private Job job;

//     private String resumeLink;

//     @Enumerated(EnumType.STRING)
//     private ApplicationStatus status = ApplicationStatus.APPLIED;

//     // ===== GETTERS & SETTERS =====

//     public Long getId() {
//         return id;
//     }

//     public User getStudent() {
//         return student;
//     }

//     public void setStudent(User student) {
//         this.student = student;
//     }

//     public Job getJob() {
//         return job;
//     }

//     public void setJob(Job job) {
//         this.job = job;
//     }

//     public String getResumeLink() {
//         return resumeLink;
//     }

//     public void setResumeLink(String resumeLink) {
//         this.resumeLink = resumeLink;
//     }

//     public ApplicationStatus getStatus() {
//         return status;
//     }

//     public void setStatus(ApplicationStatus status) {
//         this.status = status;
//     }
// }







package com.college.platform.alumni_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {

    public enum ApplicationStatus {
        APPLIED, SELECTED, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"password"})
    private User student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    @JsonIgnoreProperties({"alumni", "applications"})
    private Job job;

    private String resumeLink;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    // ── Getters & Setters ──

    public Long getId() { return id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public String getResumeLink() { return resumeLink; }
    public void setResumeLink(String resumeLink) { this.resumeLink = resumeLink; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
}