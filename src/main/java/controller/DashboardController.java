package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.repository.StudentRepository;
import com.madhavi.placementportal.repository.CompanyRepository;
import com.madhavi.placementportal.repository.JobRepository;
import com.madhavi.placementportal.repository.ApplyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @GetMapping
    public Map<String, Long> getDashboardData() {

        Map<String, Long> data = new HashMap<>();

        data.put("students", studentRepository.count());
        data.put("companies", companyRepository.count());
        data.put("jobs", jobRepository.count());
        data.put("applications", applyJobRepository.count());

        return data;
    }
}