// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class Certificate {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String certificateName;

//     @ManyToOne
//     private User student;

//     @ManyToOne
//     private Job job;
// }



package com.college.platform.alumni_platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String certificateName;

    private LocalDate issuedAt;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"password"})
    private User student;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonIgnoreProperties({"alumni", "applications"})
    private Job job;

    public Certificate() {
        this.issuedAt = LocalDate.now();
    }

    public Long getId() { return id; }

    public String getCertificateName() { return certificateName; }
    public void setCertificateName(String certificateName) { this.certificateName = certificateName; }

    public LocalDate getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDate issuedAt) { this.issuedAt = issuedAt; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
}