// package com.gyanpath.security.service.impl;

// import com.gyanpath.security.dto.JwtResponse;
// import com.gyanpath.security.service.RoleService;
// import jakarta.transaction.Transactional;
// import lombok.extern.slf4j.Slf4j;
// import com.gyanpath.security.dto.UserDto;
// import com.gyanpath.security.entity.User;
// import com.gyanpath.security.repo.UserRepo;
// import com.gyanpath.security.service.GoogleAuthService;
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

// import java.util.Map;
// import java.util.UUID;


// @Service
// @Slf4j
// public class GoogleAuthServiceImpl implements GoogleAuthService {


//     @Value("${spring.security.oauth2.client.registration.google.client-id}")
//     private String clientId;

//     @Value("${spring.security.oauth2.client.registration.google.client-secret}")
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
//         try {
//             String tokenEndpoint = "https://oauth2.googleapis.com/token";
//             MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//             params.add("code", code);
//             params.add("client_id", clientId);
//             params.add("client_secret", clientSecret);
//             params.add("redirect_uri", "http://localhost:8083/login/oauth2/code/google");
//             params.add("grant_type", "authorization_code");
//             HttpHeaders headers = new HttpHeaders();
//             headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//             HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//             ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenEndpoint, request, Map.class);
//             String idToken = (String) tokenResponse.getBody().get("id_token");
//             String userInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
//             ResponseEntity<Map> userInfoResponse = restTemplate.getForEntity(userInfoUrl, Map.class);
//             if (userInfoResponse.getStatusCode() == HttpStatus.OK) {
//                 Map<String, Object> userInfo = userInfoResponse.getBody();
//                 String email = (String) userInfo.get("email");
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
