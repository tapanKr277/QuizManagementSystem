package com.gyanpath.security.service.impl;

import com.gyanpath.security.controller.UserRolesDto;
import com.gyanpath.security.dto.ChangePasswordDto;
import com.gyanpath.security.dto.RoleDto;
import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.Role;
import com.gyanpath.security.entity.User;
import com.gyanpath.security.exception.PasswordMisMatchException;
import com.gyanpath.security.exception.ResourceNotFoundException;
import com.gyanpath.security.exception.UserNotFoundException;
import com.gyanpath.security.mapper.UserMapper;
import com.gyanpath.security.repo.UserRepo;
import com.gyanpath.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User getUserByUserName(String userName) throws ResourceNotFoundException {
		Optional<User> user = userRepo.findByUsername(userName);
		if(user.isEmpty()){
			throw new ResourceNotFoundException("User not found with this username "+ userName);
		}
		return user.get();
	}

	@Override
	public UserDto getUserDtoByUserName(String username) throws ResourceNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);
		if(user.isEmpty()){
			throw new ResourceNotFoundException("User not found with this username "+ username);
		}
		UserDto userDto = UserMapper.userToUserDto(user.get());
		return userDto;
	}

	@Override
	public UserDto getUserByEmail(String email) throws UserNotFoundException {
		Optional<User> user = userRepo.findByEmail(email);
		if(user.isEmpty()){
			throw new UserNotFoundException("User with this email not found "+ email);
		}
		return UserMapper.userToUserDto(user.get());
	}

	@Override
	public List<UserDto> getAllUserList() {
		List<User> userList = userRepo.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		userList.forEach(user-> userDtoList.add(UserMapper.userToUserDto(user)));
		return userDtoList;
	}

	@Override
	public UserDto getUserById(Short userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not found with this "+ userId+" id"));
		return UserMapper.userToUserDto(user);
	}

	@Override
	public UserDto updateUserData(UserDto userDto) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(userDto.getUserId());
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found");
		}
		User exisitingUser = user.get();
		exisitingUser.setIsActive(userDto.getIsActive());
		exisitingUser.setFirstName(userDto.getFirstName());
		exisitingUser.setLastName(userDto.getLastName());
		exisitingUser.setIsVerified(userDto.getIsVerified());
		exisitingUser.setPhoneNumber(userDto.getPhoneNumber());
		exisitingUser.setIsOtpVerified(userDto.getIsOtpVerified());
		return UserMapper.userToUserDto(userRepo.save(exisitingUser));
	}

	@Override
	public UserDto updateUserPartialData(UserDto userDto) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(userDto.getUserId());
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found");
		}
		User exisitingUser = user.get();
		exisitingUser.setFirstName(userDto.getFirstName());
		exisitingUser.setLastName(userDto.getLastName());
		exisitingUser.setPhoneNumber(userDto.getPhoneNumber());
		User updatedUser = userRepo.save(exisitingUser);
		return UserMapper.userToUserDto(updatedUser);
	}

	@Override
	public boolean deleteUser(Short userId) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(userId);
		if(user.isEmpty()){
			throw new UserNotFoundException("User not found");
		}
		user.get().setRoles(null);
		userRepo.delete(user.get());
		return true;
	}

}
