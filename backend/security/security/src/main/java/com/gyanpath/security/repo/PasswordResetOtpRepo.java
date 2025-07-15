package com.gyanpath.security.repo;

import com.gyanpath.security.entity.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordResetOtpRepo extends JpaRepository<PasswordResetOtp, Short> {

    @Query("SELECT p FROM PasswordResetOtp p WHERE p.email = :email AND p.otp = :otp")
    Optional<PasswordResetOtp> findByEmailAndOtp(@Param("email") String email, @Param("otp") String otp);


    void deleteByEmail(String email);

    void deleteByOtp(String otp);

    Optional<PasswordResetOtp> findByEmail(String email);

    Optional<PasswordResetOtp> findByOtp(String otp);
}
