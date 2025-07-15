package com.gyanpath.security.service;

import com.gyanpath.security.controller.UserRolesDto;
import com.gyanpath.security.dto.RoleDto;
import com.gyanpath.security.entity.Role;
import com.gyanpath.security.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {

    public List<RoleDto> getAllRoles();
    public Role getRoleById(Short id) throws ResourceNotFoundException;
    public void deleteRoleById(Short id) throws ResourceNotFoundException;
    public void createRole(Role role);

    public void assignRole(Short userId, Short roleId) throws ResourceNotFoundException;
    public void unAssignRole(Short userId, Short roleId) throws ResourceNotFoundException;

}
