// package com.gyanpath.security.service.impl;

// import com.gyanpath.security.dto.JwtResponse;
// import com.gyanpath.security.service.GitHubAuthService;
// import com.gyanpath.security.service.RoleService;
// import jakarta.transaction.Transactional;
// import lombok.extern.slf4j.Slf4j;
// import com.gyanpath.security.dto.UserDto;
// import com.gyanpath.security.entity.User;
// import com.gyanpath.security.repo.UserRepo;
// import com.gyanpath.security.service.JwtTokenService;
// import com.gyanpath.security.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;
// import org.springframework.http.*;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.client.RestTemplate;

// import java.util.Collections;
// import java.util.Map;
// import java.util.UUID;

// @Service
// @Slf4j
// public class GitHubAuthServiceImpl implements GitHubAuthService {

//     @Value("${spring.security.oauth2.client.registration.github.client-id}")
//     private String clientId;

//     @Value("${spring.security.oauth2.client.registration.github.client-secret}")
//     private String clientSecret;

//     @Autowired
//     private RestTemplate restTemplate;

//     @Autowired
//     UserDetailsServiceImpl userDetailsService;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private UserRepo userRepository;

//     @Autowired
//     private JwtTokenService jwtTokenService;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private RoleService roleService;

//     @Override
//     @Transactional
//     public JwtResponse handleCallBack(String code) throws Exception {
//     	log.info(code+"---------"+"code found"+"----------");;
//         try {
//             String tokenEndpoint = "https://github.com/login/oauth/access_token";
//             MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//             params.add("code", code);
//             params.add("client_id", clientId);
//             params.add("client_secret", clientSecret);
//             params.add("redirect_uri", "http://localhost:8080/api/auth/github/callback");
//             params.add("grant_type", "authorization_code");
//             HttpHeaders headers = new HttpHeaders();
//             headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//             headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//             HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//             ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
//             String accessToken = (String) tokenResponse.getBody().get("access_token");
//             String userInfoUrl = "https://api.github.com/user";
//             HttpHeaders userInfoHeaders = new HttpHeaders();
//             userInfoHeaders.setBearerAuth(accessToken);
//             HttpEntity<Void> userInfoRequest = new HttpEntity<>(userInfoHeaders);
//             ResponseEntity<Map> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequest, Map.class);
//             if (userInfoResponse.getStatusCode() == HttpStatus.OK) {
//                 Map<String, Object> userInfo = userInfoResponse.getBody();
//                 String email = (String) userInfo.get("email");
//                 if (email == null) {
//                     throw new Exception("Email not available from GitHub");
//                 }
//                 UserDetails userDetails = null;
//                 try {
//                     userDetails = userDetailsService.loadUserByUsername(email);
//                 } catch (Exception e) {
//                     User user = new User();
//                     user.setEmail(email);
//                     user.setUsername(email);
//                     user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
//                     userDetailsService.addUserByOAuth(user);
//                 }
//                 UserDto userDto = userService.getUserByEmail(email);
//                 String token = jwtTokenService.generateToken(userDto);
//                 String refreshToken = jwtTokenService.generateRefreshToken(userDto);
//                 return new JwtResponse(token, refreshToken, userDto.getEmail(), userDto.getUserId(), userDto.getUsername());
//             }
//             throw new Exception("Internal Server error");
//         } catch (Exception e) {
//             log.info("Exception while oauth" + e.getMessage());
//             throw new Exception(e.getMessage());
//         }
//     }
// }
