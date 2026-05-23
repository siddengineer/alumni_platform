




package com.college.platform.alumni_platform.service;

import com.college.platform.alumni_platform.entity.Application;
import com.college.platform.alumni_platform.entity.Job;
import com.college.platform.alumni_platform.entity.User;
import com.college.platform.alumni_platform.repository.ApplicationRepository;
import com.college.platform.alumni_platform.repository.JobRepository;
import com.college.platform.alumni_platform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public ApplicationService(UserRepository userRepository,
                              JobRepository jobRepository,
                              ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    @Transactional
    public Application apply(String email, Long jobId, String resumeLink) {

        // findByEmail returns a fully managed entity within this transaction
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (job.getStatus() != Job.JobStatus.APPROVED) {
            throw new IllegalStateException("Job is not open for applications");
        }

        if (applicationRepository.findByStudentAndJob(student, job).isPresent()) {
            throw new IllegalStateException("You have already applied for this job");
        }

        Application application = new Application();

        // FIX: use getReferenceById to get a managed proxy — prevents detached entity error
        application.setStudent(userRepository.getReferenceById(student.getId()));
        application.setJob(jobRepository.getReferenceById(job.getId()));
        application.setResumeLink(resumeLink);

        return applicationRepository.save(application);
    }

    @Transactional(readOnly = true)
    public List<Application> getApplicationsForStudent(String email) {
        User student = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return applicationRepository.findByStudent(student);
    }
}