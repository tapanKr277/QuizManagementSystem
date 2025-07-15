package com.gyanpath.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private String email;
    private Short userId;
    private String username;
}
