package com.gyanpath.security.mapper;

import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.User;

public class UserMapper {

    public static User userDtoToUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if(userDto.getPassword()!=null){
            user.setPassword(userDto.getPassword());
        }
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setIsVerified(userDto.getIsVerified());
        user.setIsOtpVerified(userDto.getIsOtpVerified());
        user.setIsActive(true);
        return user;
    }

    public static UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLastUpdate(user.getLastUpdate());
        userDto.setEmail(user.getEmail());
        userDto.setIsActive(user.getIsActive());
        userDto.setIsVerified(user.getIsVerified());
        userDto.setIsOtpVerified(user.getIsOtpVerified());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setOtpLastUpdate(user.getOtpLastUpdate());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setLastUpdate(user.getLastUpdate());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
