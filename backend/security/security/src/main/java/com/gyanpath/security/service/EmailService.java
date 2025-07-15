package com.gyanpath.security.service;

import com.gyanpath.security.dto.EmailDetails;

public interface EmailService {

    public String sendSimpleMail(EmailDetails details);

    public String sendMailWithAttachment(EmailDetails details);

    public boolean sendEmailOtp(String email, String otp);

    public boolean sendVerificationEmail(String email, String token);
}
