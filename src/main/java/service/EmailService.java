package com.madhavi.placementportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String toEmail, String otp) {

        System.out.println("OTP = " + otp);

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(toEmail);
            message.setSubject("Placement Portal OTP Verification");
            message.setText("Your OTP is: " + otp);

            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Email sending failed");
            e.printStackTrace();
        }
    }
}