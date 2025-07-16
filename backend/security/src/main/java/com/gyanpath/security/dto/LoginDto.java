package com.gyanpath.security.dto;

import com.gyanpath.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

//    private String username;
    private String email;
    private String password;

}
