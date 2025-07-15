package com.gyanpath.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyanpath.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Short userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private Boolean isActive;
    private Boolean isVerified;
    private Boolean isOtpVerified;
    private LocalDateTime otpLastUpdate;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;
    private Set<Role> roles;

}
