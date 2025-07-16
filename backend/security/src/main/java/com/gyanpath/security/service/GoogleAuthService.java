package com.gyanpath.security.service;

import com.gyanpath.security.dto.JwtResponse;

public interface GoogleAuthService {

    JwtResponse handleCallBack(String code) throws Exception;
}
