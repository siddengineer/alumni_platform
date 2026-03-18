// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class Job {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String description;
//     private String requiredSkills;
//     private String duration;
//     private Double paymentAmount;

//     @Enumerated(EnumType.STRING)
//     private JobStatus status;

//     @ManyToOne
//     private User alumni;

//     @ManyToOne
//     private User student;

//     public enum JobStatus {
//         PENDING,
//         APPROVED,
//         ASSIGNED,
//         IN_PROGRESS,
//         COMPLETED
//     }
// }
// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// public class Job {

//     public enum JobStatus {
//         PENDING, APPROVED, ASSIGNED, COMPLETED
//     }

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String description;
//     private String requiredSkills;
//     private String duration;
//     private Double paymentAmount;

//     @Enumerated(EnumType.STRING)
//     private JobStatus status;

//     @ManyToOne
//     private User alumni;

//     public Long getId() {
//         return id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getRequiredSkills() {
//         return requiredSkills;
//     }

//     public void setRequiredSkills(String requiredSkills) {
//         this.requiredSkills = requiredSkills;
//     }

//     public String getDuration() {
//         return duration;
//     }

//     public void setDuration(String duration) {
//         this.duration = duration;
//     }

//     public Double getPaymentAmount() {
//         return paymentAmount;
//     }

//     public void setPaymentAmount(Double paymentAmount) {
//         this.paymentAmount = paymentAmount;
//     }

//     public JobStatus getStatus() {
//         return status;
//     }

//     public void setStatus(JobStatus status) {
//         this.status = status;
//     }

//     public User getAlumni() {
//         return alumni;
//     }

//     public void setAlumni(User alumni) {
//         this.alumni = alumni;
//     }
// }








// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;

// @Entity
// public class Job {

//     public enum JobStatus {
//         PENDING,
//         APPROVED,
//         ASSIGNED,
//         COMPLETED
//     }

//     public enum PaymentStatus {
//         HELD,
//         RELEASED
//     }

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String description;
//     private String requiredSkills;
//     private String duration;
//     private Double paymentAmount;
//     private String razorpayOrderId;
//     @Enumerated(EnumType.STRING)
//     private JobStatus status;

//     @Enumerated(EnumType.STRING)
//     private PaymentStatus paymentStatus = PaymentStatus.HELD;

//     @ManyToOne
//     private User alumni;

//     // ===== GETTERS & SETTERS =====

//     public Long getId() {
//         return id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public String getRequiredSkills() {
//         return requiredSkills;
//     }

//     public void setRequiredSkills(String requiredSkills) {
//         this.requiredSkills = requiredSkills;
//     }

//     public String getDuration() {
//         return duration;
//     }

//     public void setDuration(String duration) {
//         this.duration = duration;
//     }

//     public Double getPaymentAmount() {
//         return paymentAmount;
//     }

//     public void setPaymentAmount(Double paymentAmount) {
//         this.paymentAmount = paymentAmount;
//     }

//     public JobStatus getStatus() {
//         return status;
//     }

//     public void setStatus(JobStatus status) {
//         this.status = status;
//     }

//     public PaymentStatus getPaymentStatus() {
//         return paymentStatus;
//     }

//     public void setPaymentStatus(PaymentStatus paymentStatus) {
//         this.paymentStatus = paymentStatus;
//     }

//     public User getAlumni() {
//         return alumni;
//     }

//     public void setAlumni(User alumni) {
//         this.alumni = alumni;
//     }
// }






package com.college.platform.alumni_platform.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job implements Serializable {

    public enum JobStatus {
        PENDING,
        APPROVED,
        ASSIGNED,
        COMPLETED
    }

    public enum PaymentStatus {
        HELD,
        RELEASED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String requiredSkills;
    private String duration;
    private Double paymentAmount;

    // ✅ ADD COLUMN MAPPING (important)
    @Column(name = "razorpay_order_id")
    private String razorpayOrderId;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.HELD;

    @ManyToOne
    @JoinColumn(name = "alumni_id")
    private User alumni;

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public User getAlumni() {
        return alumni;
    }

    public void setAlumni(User alumni) {
        this.alumni = alumni;
    }

    // ✅ VERY IMPORTANT (missing earlier)

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }
}