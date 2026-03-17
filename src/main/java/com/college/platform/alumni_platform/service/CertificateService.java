package com.college.platform.alumni_platform.service;

import com.college.platform.alumni_platform.entity.Certificate;
import com.college.platform.alumni_platform.repository.CertificateRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @Cacheable("certificates")
    public List<Certificate> getAllCertificates() {
        System.out.println("Fetching certificates from DB...");
        return certificateRepository.findAll();
    }
}