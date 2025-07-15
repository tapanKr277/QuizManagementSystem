package com.gyanpath.security.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Short roleId;
    private String role;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;
}
