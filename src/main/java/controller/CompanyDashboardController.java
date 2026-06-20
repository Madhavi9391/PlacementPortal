package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.repository.ApplyJobRepository;
import com.madhavi.placementportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company-dashboard")
public class CompanyDashboardController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @GetMapping
    public Map<String, Long> getDashboard(
            @RequestParam String companyName) {

        Map<String, Long> data = new HashMap<>();

        data.put("jobsPosted",
                jobRepository.countByCompanyName(companyName));

        data.put("applicationsReceived",
                applyJobRepository.countByCompanyName(companyName));

        return data;
    }
}