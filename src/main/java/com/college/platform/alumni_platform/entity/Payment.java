// package com.college.platform.alumni_platform.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// public class Payment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Double amount;

//     @Enumerated(EnumType.STRING)
//     private PaymentStatus status;

//     @OneToOne
//     private Job job;

//     public enum PaymentStatus {
//         HELD,
//         PAID
//     }
// }
package com.college.platform.alumni_platform.entity;

import jakarta.persistence.*;

@Entity
public class Payment {

    public enum PaymentStatus {
        HELD, RELEASED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne
    private Job job;

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
