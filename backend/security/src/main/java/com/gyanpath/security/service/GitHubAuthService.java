package com.gyanpath.security.service;

import com.gyanpath.security.dto.JwtResponse;

public interface GitHubAuthService {

    JwtResponse handleCallBack(String code) throws Exception;
}
