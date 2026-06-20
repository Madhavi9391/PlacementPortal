package com.madhavi.placementportal.controller;

import com.madhavi.placementportal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private EmailService emailService;
    private String generatedOtp;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        generatedOtp=otp;

        emailService.sendOtp(email, otp);

        return "OTP Sent Successfully: ";
    }
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String otp) {

        if (otp.equals(generatedOtp)) {
            return "OTP Verified Successfully";
        }

        return "Invalid OTP";
    }
}