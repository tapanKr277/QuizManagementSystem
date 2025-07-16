package com.gyanpath.security.service;

import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.JwtToken;

public interface JwtTokenService {

    public String generateToken(UserDto userDto);


    public JwtToken getJwtTokenByUserIdAndBlacklisted(Short userId, Boolean blackListed);

    JwtToken getJwtTokenByToken(String token);

    JwtToken saveToken(JwtToken jwtToken);

    String generateRefreshToken(UserDto userDto);
}
