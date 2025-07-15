package com.gyanpath.security.service;

import com.gyanpath.security.dto.VerificationTokenDto;
import com.gyanpath.security.entity.VerificationToken;

import java.util.List;

public interface VerificationTokenService {

    public VerificationToken createVerificationToken(String email);

    public boolean validateVerificationToken(String token, String email);

    public VerificationToken resendVerificationToken(String email);

    public void deleteVerificationToken(String email);

    List<VerificationTokenDto> getAllTokenList();
}
