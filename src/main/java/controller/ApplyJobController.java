package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.entity.ApplyJob;
import com.madhavi.placementportal.repository.ApplyJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/applications")
public class ApplyJobController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @PostMapping("/apply")
    public ApplyJob applyJob(@RequestBody ApplyJob applyJob) {
        return applyJobRepository.save(applyJob);
    }

    @GetMapping
    public List<ApplyJob> getAllApplications() {
        return applyJobRepository.findAll();
    }
    @PutMapping("/{id}/status")
    public ApplyJob updateStatus(@PathVariable Long id,
                                 @RequestParam String status) {

        ApplyJob applyJob =
                applyJobRepository.findById(id).orElse(null);

        if (applyJob != null) {

            applyJob.setStatus(status);

            return applyJobRepository.save(applyJob);
        }

        return null;
    }
    @GetMapping("/company/{companyName}")
    public List<ApplyJob> getApplicationsByCompany(
            @PathVariable String companyName) {

        return applyJobRepository.findByCompanyName(companyName);
    }
    @GetMapping("/count")
    public Map<String,Integer> getApplicationCount(){

        List<ApplyJob> applications = applyJobRepository.findAll();

        int applied = applications.size();
        int shortlisted = 0;
        int selected = 0;

        for(ApplyJob app : applications){

            if(app.getStatus().equals("Shortlisted")){
                shortlisted++;
            }

            if(app.getStatus().equals("Selected")){
                selected++;
            }
        }


        Map<String,Integer> result = new HashMap<>();

        result.put("applicationsApplied", applied);
        result.put("shortlisted", shortlisted);
        result.put("selected", selected);

        return result;
    }
}