package com.gyanpath.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetOtpDto {

    private Short id;
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

}
