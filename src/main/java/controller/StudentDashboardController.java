package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.repository.ApplyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/student-dashboard")
public class StudentDashboardController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @GetMapping
    public Map<String, Long> getDashboard(
            @RequestParam String studentEmail) {

        Map<String, Long> data = new HashMap<>();

        data.put("applicationsApplied",
                applyJobRepository.countByStudentEmail(studentEmail));

        data.put("shortlisted",
                applyJobRepository.countByStudentEmailAndStatus(
                        studentEmail, "Shortlisted"));

        data.put("selected",
                applyJobRepository.countByStudentEmailAndStatus(
                        studentEmail, "Selected"));

        return data;
    }
}