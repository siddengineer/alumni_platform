// package com.college.platform.alumni_platform.repository;

// import com.college.platform.alumni_platform.entity.Certificate;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface CertificateRepository extends JpaRepository<Certificate, Long> {
// }


package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Certificate;
import com.college.platform.alumni_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    // Student can fetch their own certificates
    List<Certificate> findByStudent(User student);

    // Admin can see certs per job
    List<Certificate> findByJob_Id(Long jobId);
}