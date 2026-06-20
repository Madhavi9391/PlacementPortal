package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.entity.Company;
import com.madhavi.placementportal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/register")
    public Company registerCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping("/login")
    public String loginCompany(@RequestBody Company company) {

        Company existingCompany =
                companyRepository.findByEmailAndPassword(
                        company.getEmail(),
                        company.getPassword()
                );

        if (existingCompany != null) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }
}