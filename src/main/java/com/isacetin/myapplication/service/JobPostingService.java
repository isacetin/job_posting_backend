package com.isacetin.myapplication.service;

import com.isacetin.myapplication.domain.JobPosting;
import com.isacetin.myapplication.domain.User;
import com.isacetin.myapplication.domain.enumeration.JobStatus;
import com.isacetin.myapplication.repository.JobPostingRepository;
import com.isacetin.myapplication.security.SecurityUtils;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final UserService userService;

    public JobPostingService(JobPostingRepository jobPostingRepository, UserService userService) {
        this.jobPostingRepository = jobPostingRepository;
        this.userService = userService;
    }

    public JobPosting save(JobPosting jobPosting) {
        jobPosting.setCreatedDate(Instant.now());
        jobPosting.setStatus(JobStatus.ACTIVE);
        jobPosting.setUser(userService.getUserWithAuthorities().orElseThrow());
        return jobPostingRepository.save(jobPosting);
    }

    @Transactional(readOnly = true)
    public List<JobPosting> findAllActive() {
        return jobPostingRepository.findByStatusOrderByCreatedDateDesc(JobStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public List<JobPosting> findByCurrentUser() {
        User currentUser = userService.getUserWithAuthorities().orElseThrow();
        return jobPostingRepository.findByUserOrderByCreatedDateDesc(currentUser);
    }

    @Transactional(readOnly = true)
    public JobPosting findOne(Long id) {
        return jobPostingRepository.findById(id).orElseThrow(() -> new RuntimeException("İş ilanı bulunamadı"));
    }
}
