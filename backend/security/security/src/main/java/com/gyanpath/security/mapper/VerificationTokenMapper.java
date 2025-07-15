package com.gyanpath.security.mapper;

import com.gyanpath.security.dto.VerificationTokenDto;
import com.gyanpath.security.entity.VerificationToken;

public class VerificationTokenMapper {

    // Method to map VerificationTokenDto to VerificationToken entity
    public static VerificationToken verificationTokenDtoToVerificationToken(VerificationTokenDto verificationTokenDto) {
        if (verificationTokenDto == null) {
            return null;
        }

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setVerificationTokenId(verificationTokenDto.getVerificationTokenId());
        verificationToken.setEmail(verificationTokenDto.getEmail());
        verificationToken.setToken(verificationTokenDto.getToken());
        verificationToken.setExpiryTime(verificationTokenDto.getExpiryTime());
        verificationToken.setCreatedAt(verificationTokenDto.getCreatedAt());
        verificationToken.setLastUpdate(verificationTokenDto.getLastUpdate());

        return verificationToken;
    }

    // Method to map VerificationToken entity to VerificationTokenDto
    public static VerificationTokenDto verificationTokenToVerificationTokenDto(VerificationToken verificationToken) {
        if (verificationToken == null) {
            return null;
        }

        VerificationTokenDto verificationTokenDto = new VerificationTokenDto();
        verificationTokenDto.setVerificationTokenId(verificationToken.getVerificationTokenId());
        verificationTokenDto.setEmail(verificationToken.getEmail());
        verificationTokenDto.setToken(verificationToken.getToken());
        verificationTokenDto.setExpiryTime(verificationToken.getExpiryTime());
        verificationTokenDto.setCreatedAt(verificationToken.getCreatedAt());
        verificationTokenDto.setLastUpdate(verificationToken.getLastUpdate());

        return verificationTokenDto;
    }
}
