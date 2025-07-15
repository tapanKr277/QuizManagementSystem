package com.gyanpath.security.mapper;

import com.gyanpath.security.dto.UpdateUserDataDto;
import com.gyanpath.security.entity.User;

public class UpdateUserDataMapper {

    public static User updateUserDataDtoToUser(UpdateUserDataDto updateUserDataDto) {
        User user = new User();
        user.setFirstName(updateUserDataDto.getFirstName());
        user.setLastName(updateUserDataDto.getLastName());
        if(updateUserDataDto.getPassword()!=null){
            user.setPassword(updateUserDataDto.getPassword());
        }
        user.setPhoneNumber(updateUserDataDto.getPhoneNumber());
        user.setIsActive(updateUserDataDto.getIsActive());
        user.setIsVerified(updateUserDataDto.getIsVerified());
        user.setIsOtpVerified(updateUserDataDto.getIsOtpVerified());
        return user;
    }

}
