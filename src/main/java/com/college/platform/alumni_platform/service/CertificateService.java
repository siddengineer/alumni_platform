// package com.college.platform.alumni_platform.service;

// import com.college.platform.alumni_platform.entity.Certificate;
// import com.college.platform.alumni_platform.repository.CertificateRepository;
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class CertificateService {

//     private final CertificateRepository certificateRepository;

//     public CertificateService(CertificateRepository certificateRepository) {
//         this.certificateRepository = certificateRepository;
//     }

//     @Cacheable("certificates")
//     public List<Certificate> getAllCertificates() {
//         System.out.println("Fetching certificates from DB...");
//         return certificateRepository.findAll();
//     }
// }



package com.college.platform.alumni_platform.service;

import com.college.platform.alumni_platform.entity.Certificate;
import com.college.platform.alumni_platform.repository.CertificateRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    // BUG FIX: This was @Cacheable but the cache was NEVER evicted anywhere.
    // After a certificate is issued, all callers would get the old empty list forever.
    // Removed @Cacheable here — caching certificates globally doesn't make sense
    // since each student needs their own filtered view. Student-specific queries
    // are fast indexed lookups and don't need caching.
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    // If you still want caching, use a per-student key and evict it in AdminController.
    // Example (optional, only enable if you add @CacheEvict in AdminController too):
    // @Cacheable(value = "studentCertificates", key = "#studentId")
    // public List<Certificate> getCertificatesForStudent(Long studentId) { ... }
}