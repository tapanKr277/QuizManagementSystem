package com.gyanpath.security.service.impl;

import com.gyanpath.security.service.EmailService;
import com.gyanpath.security.service.OtpService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private final Random random = new Random();

    private EmailService emailService;

    @Override
    public String generateOtp() {
        int otp = random.nextInt(999999);
        return  String.format("%06d", otp);
    }

    @Override
    public boolean sendOTP(String email, String otp) {
        if(emailService.sendEmailOtp(email, otp)){
            return true;
        }
        return false;
    }
}
