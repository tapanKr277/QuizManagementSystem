package com.gyanpath.security.service;

import com.gyanpath.security.dto.PasswordResetOtpDto;
import com.gyanpath.security.entity.PasswordResetOtp;
import com.gyanpath.security.exception.InvalidOtpException;
import com.gyanpath.security.exception.OtpNotFound;

import java.util.List;

public interface PasswordResetOtpService {

    public PasswordResetOtp createOtp(String email);

    public boolean validateOtp(String email, String otp) throws InvalidOtpException;

    public void deleteOtpByEmail(String email);

    public void deleteOtpByOtp(String otp);

    List<PasswordResetOtpDto> getAllOtpList();
}
