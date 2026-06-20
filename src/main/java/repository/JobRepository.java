package com.madhavi.placementportal.repository;

import com.madhavi.placementportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobRepository extends JpaRepository<Job, Long> {
List<Job> findByJobTitleContainingIgnoreCase(String jobTitle);
List<Job> findByCompanyNameContainingIgnoreCase(String companyName);
List<Job> findByLocationContainingIgnoreCase(String location);
    long countByCompanyName(String companyName);

}