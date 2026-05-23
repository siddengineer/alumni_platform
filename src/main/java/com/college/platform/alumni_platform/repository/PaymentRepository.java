// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Payment;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface PaymentRepository extends JpaRepository<Payment, Long> {
// }


// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Payment;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface PaymentRepository extends JpaRepository<Payment, Long> {

//     // 🔐 Used for idempotency protection
//     Optional<Payment> findByRazorpayPaymentId(String razorpayPaymentId);

//     // 🔍 Optional (recommended) – if you want to link webhook to existing order
//     Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
// }





package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByRazorpayPaymentId(String razorpayPaymentId);

    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);

    // BUG FIX: needed by AdminController.approveJob() to prevent duplicate payments
    Optional<Payment> findByJob(Job job);
}