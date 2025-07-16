package com.gyanpath.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gyanpath.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDataDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Short userId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String username;
    private String firstName;
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    private Boolean isActive;
    private Boolean isVerified;
    private Boolean isOtpVerified;

    private LocalDateTime otpLastUpdate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastUpdate;

}
