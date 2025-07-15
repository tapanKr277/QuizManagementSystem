package com.gyanpath.security.service.impl;

import com.gyanpath.security.dto.PasswordResetOtpDto;
import com.gyanpath.security.entity.PasswordResetOtp;
import com.gyanpath.security.exception.InvalidOtpException;
import com.gyanpath.security.exception.OtpNotFound;
import com.gyanpath.security.mapper.PasswordResetOtpMapper;
import com.gyanpath.security.repo.PasswordResetOtpRepo;
import com.gyanpath.security.service.PasswordResetOtpService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PasswordRestOtpServiceImpl implements PasswordResetOtpService {

    @Autowired
    private PasswordResetOtpRepo passwordResetOtpRepo;

    private SecureRandom random = new SecureRandom();


    public String generateOtp(){
        int otp = 1000 + random.nextInt(9000);
        return String.valueOf(otp);
    }

    @Override
    @Transactional
    public PasswordResetOtp createOtp(String email){
        PasswordResetOtp passwordRestOtp = new PasswordResetOtp();
        String otp = generateOtp();
        passwordRestOtp.setOtp(otp);
        passwordRestOtp.setEmail(email);
        passwordRestOtp.setExpiryTime(LocalDateTime.now().plusMinutes(3));
        return passwordResetOtpRepo.save(passwordRestOtp);
    }

    @Override
    public boolean validateOtp(String email, String otp) throws InvalidOtpException {

        Optional<PasswordResetOtp> otpEntities = passwordResetOtpRepo.findByEmailAndOtp(email, otp);

        if (otpEntities.isEmpty()) {
            throw new InvalidOtpException("Invalid OTP");
        }
        PasswordResetOtp otpEntity = otpEntities.get();

        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP has expired");
        }

        return true;
    }


    @Override
    @Transactional
    public void deleteOtpByEmail(String email)  {
        Optional<PasswordResetOtp> passwordResetOtp = passwordResetOtpRepo.findByEmail(email);
        if(passwordResetOtp.isPresent()){
            passwordResetOtpRepo.deleteByEmail(email);
        }

    }

    @Override
    @Transactional
    public void deleteOtpByOtp(String otp)   {
        Optional<PasswordResetOtp> passwordResetOtp = passwordResetOtpRepo.findByOtp(otp);
        if(passwordResetOtp.isPresent()){
            passwordResetOtpRepo.deleteByEmail(otp);
        }
    }

    @Override
    public List<PasswordResetOtpDto> getAllOtpList() {
        List<PasswordResetOtp> otpList = passwordResetOtpRepo.findAll();
        List<PasswordResetOtpDto> otpDtoList = new ArrayList<>();
        otpList.forEach(otp-> otpDtoList.add(PasswordResetOtpMapper.passwordResetOtpToPasswordResetOtpDto(otp)));
        return otpDtoList;
    }
}
