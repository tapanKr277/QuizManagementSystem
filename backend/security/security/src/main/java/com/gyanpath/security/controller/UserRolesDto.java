package com.gyanpath.security.controller;

import com.gyanpath.security.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRolesDto {

    private Short userId;
    private String username;
    private List<RoleDto> roleDtoList;

}
