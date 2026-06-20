package com.madhavi.placementportal.repository;

import com.madhavi.placementportal.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByEmailAndPassword(String email, String password);
}
