package com.gyanpath.security.service;

import com.gyanpath.security.controller.UserRolesDto;
import com.gyanpath.security.dto.ChangePasswordDto;
import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.User;
import com.gyanpath.security.exception.PasswordMisMatchException;
import com.gyanpath.security.exception.ResourceNotFoundException;
import com.gyanpath.security.exception.UserNotFoundException;

import java.util.List;

public interface UserService {


    public User getUserByUserName(String userName) throws ResourceNotFoundException;

    UserDto getUserDtoByUserName(String username) throws ResourceNotFoundException;

    UserDto getUserByEmail(String email) throws UserNotFoundException;

    List<UserDto> getAllUserList();

    UserDto getUserById(Short userId);

    UserDto updateUserData(UserDto userDto) throws UserNotFoundException;

    UserDto updateUserPartialData(UserDto userDto) throws UserNotFoundException;

    boolean deleteUser(Short userId) throws UserNotFoundException;

    void initializeRolesAndAdmin();
}
