package com.gyanpath.security.service.impl;

import com.gyanpath.security.controller.UserRolesDto;
import com.gyanpath.security.dto.RoleDto;
import com.gyanpath.security.entity.Role;
import com.gyanpath.security.entity.User;
import com.gyanpath.security.exception.ResourceNotFoundException;
import com.gyanpath.security.mapper.RoleMapper;
import com.gyanpath.security.repo.RoleRepo;
import com.gyanpath.security.repo.UserRepo;
import com.gyanpath.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roleList = roleRepo.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleList.forEach(role-> roleDtoList.add(RoleMapper.roleToRoleDto(role)));
        return roleDtoList;
    }

    @Override
    public Role getRoleById(Short id) throws ResourceNotFoundException {
        Optional<Role> role = roleRepo.findById(id);
        if(role.isEmpty()){
            throw new ResourceNotFoundException("Role with this id not found "+ id);
        }
        return role.get();
    }

    @Override
    public void deleteRoleById(Short id) throws ResourceNotFoundException {
        Optional<Role> role = roleRepo.findById(id);
        if(role.isEmpty()){
            throw new ResourceNotFoundException("Role with this id not found "+ id);
        }
        roleRepo.deleteById(id);
    }

    @Override
    public void createRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void assignRole(Short userId, Short roleId) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User with this id "+ userId+" is not found");
        }
        Optional<Role> role = roleRepo.findById(roleId);
        if(role.isEmpty()){
            throw new ResourceNotFoundException("Role with this id "+ roleId+" is not found");
        }
        Set<Role> roles = user.get().getRoles();
        roles.add(role.get());
        user.get().setRoles(roles);
        userRepo.save(user.get());
    }

    @Override
    public void unAssignRole(Short userId, Short roleId) throws ResourceNotFoundException {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User with this id "+ userId+" is not found");
        }
        Optional<Role> role = roleRepo.findById(roleId);
        if(role.isEmpty()){
            throw new ResourceNotFoundException("Role with this id "+ roleId+" is not found");
        }
        Set<Role> roles = user.get().getRoles();

        boolean roleRemoved = roles.remove(role.get());

        if (!roleRemoved) {
            throw new ResourceNotFoundException("Role with id " + roleId + " is not assigned to user with id " + userId);
        }

        userRepo.save(user.get());
    }


}
