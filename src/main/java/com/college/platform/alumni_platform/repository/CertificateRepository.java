package com.college.platform.alumni_platform.repository;

import com.college.platform.alumni_platform.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
