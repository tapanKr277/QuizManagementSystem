package com.gyanpath.security.service.impl;

import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.JwtToken;
import com.gyanpath.security.entity.User;
import com.gyanpath.security.mapper.UserMapper;
import com.gyanpath.security.repo.JwtTokenRepo;
import com.gyanpath.security.repo.UserRepo;
import com.gyanpath.security.service.JwtService;
import com.gyanpath.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    private JwtTokenRepo jwtTokenRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Override
    public String generateToken(UserDto userDto) {
        invalidateOldToken(userDto.getUserId());
        String newToken = jwtService.generateToken(userDto);
        saveNewToken(userDto.getUserId(), newToken, 7);
        return newToken;
    }

    @Override
    public JwtToken getJwtTokenByUserIdAndBlacklisted(Short userId, Boolean blackListed) {
//        return jwtTokenRepo.findByUserIdAndBlacklisted(userId, blackListed)
        return  null;
    }

    @Override
    public JwtToken getJwtTokenByToken(String token) {
        return jwtTokenRepo.findByToken(token);
    }

    @Override
    public JwtToken saveToken(JwtToken jwtToken) {
        return jwtTokenRepo.save(jwtToken);
    }

    @Override
    public String generateRefreshToken(UserDto userDto) {
        String newRefreshToken = jwtService.generateRefreshToken(userDto);
        saveNewToken(userDto.getUserId(), newRefreshToken, 30);
        return newRefreshToken;
    }


    private void invalidateOldToken(Short userId){
        List<JwtToken> userTokens = jwtTokenRepo.findByUserId(userId);
        for (JwtToken token : userTokens) {
            if (!token.getBlacklisted()) {
                token.setBlacklisted(true);
                jwtTokenRepo.save(token);
            }
        }
    }


    private void saveNewToken(Short userId, String newToken, Integer expirationTime){
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(newToken);
        jwtToken.setUserId(userId);
        jwtToken.setExpirationTime(LocalDateTime.now().plusMinutes(expirationTime));
        jwtTokenRepo.save(jwtToken);
    }
}
