package com.gyanpath.security.service;

import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface JwtService {

	public String generateToken(UserDto userDto);

	public String generateRefreshToken(UserDto userDto);

	public String extractUserName(String token);

	public String extractEmail(String token);

	public boolean validateToken(String token, UserDetails userDetails);

	public Claims getClaimsFromToken(String refreshToken);

	public List<String> extractRoles(String token);
}
