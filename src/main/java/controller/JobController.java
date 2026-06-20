package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.entity.Job;
import com.madhavi.placementportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/add")
    public Job addJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {

        jobRepository.deleteById(id);

        return "Job Deleted Successfully";
    }
    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String title) {

        return jobRepository.findByJobTitleContainingIgnoreCase(title);
    }
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {

        Job job = jobRepository.findById(id).orElse(null);

        if (job != null) {
            job.setJobTitle(updatedJob.getJobTitle());
            job.setCompanyName(updatedJob.getCompanyName());
            job.setLocation(updatedJob.getLocation());
            job.setSalary(updatedJob.getSalary());

            return jobRepository.save(job);
        }

        return null;
    }
    @GetMapping("/company")
    public List<Job> searchByCompany(@RequestParam String company) {

        return jobRepository.findByCompanyNameContainingIgnoreCase(company);
    }
    @GetMapping("/location")
    public List<Job> searchByLocation(@RequestParam String location) {

        return jobRepository.findByLocationContainingIgnoreCase(location);
    }
}