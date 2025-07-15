package com.gyanpath.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

    private String oldPassword;
    private String password;
    private String confirmPassword;
    private String email;
}
