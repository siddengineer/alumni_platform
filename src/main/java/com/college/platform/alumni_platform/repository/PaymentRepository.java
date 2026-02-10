package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
