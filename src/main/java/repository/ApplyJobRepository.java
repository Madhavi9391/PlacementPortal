package com.madhavi.placementportal.repository;

import com.madhavi.placementportal.entity.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Long> {
List<ApplyJob>findByCompanyName(String companyName);
long countByCompanyName(String companyName);
long countByCompanyNameAndStatus(String companyName,String status);
long countByStudentEmail(String studentEmail);
long countByStudentEmailAndStatus(String studentEmail,String status);
}