// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.PaymentLog;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {

// }


package com.college.platform.alumni_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.platform.alumni_platform.entity.PaymentLog;

public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long> {

}