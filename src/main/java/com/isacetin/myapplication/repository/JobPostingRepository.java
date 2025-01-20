package com.isacetin.myapplication.repository;

import com.isacetin.myapplication.domain.JobPosting;
import com.isacetin.myapplication.domain.User;
import com.isacetin.myapplication.domain.enumeration.JobStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    List<JobPosting> findByUserOrderByCreatedDateDesc(User user);
    List<JobPosting> findByStatusOrderByCreatedDateDesc(JobStatus status);
}
