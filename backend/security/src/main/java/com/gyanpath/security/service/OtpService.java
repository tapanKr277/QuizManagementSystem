package com.gyanpath.security.service;


import org.springframework.stereotype.Service;

public interface OtpService {

    public String generateOtp();

    public boolean sendOTP(String email, String otp);
}
