// package com.gyanpath.security.config;

// import com.gyanpath.security.dto.UserDto;
// import com.gyanpath.security.entity.User;
// import com.gyanpath.security.exception.ResourceNotFoundException;
// import com.gyanpath.security.exception.UserNotFoundException;
// import com.gyanpath.security.service.JwtService;
// import com.gyanpath.security.service.JwtTokenService;
// import com.gyanpath.security.service.impl.UserDetailsServiceImpl;
// import com.gyanpath.security.service.impl.UserServiceImpl;
// import com.netflix.discovery.converters.Auto;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
// import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
// import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.util.UriComponentsBuilder;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// @Component
// @RequiredArgsConstructor
// @Slf4j
// public class OauthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


//     @Value("${frontend.url}")
//     private String frontendUrl;


//     @Autowired
//     private UserDetailsServiceImpl userDetailsService;

//     @Autowired
//     private JwtTokenService jwtTokenService;

//     @Autowired
//     private UserServiceImpl userService;
    
//     @Autowired
//     private JwtService jwtService;


//     private String username;
//     private String idAttributeKey;
//     private UserDto newUser;
//     private String email;

//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException{
//         OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
//         String providerName = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
//         if("github".equals(providerName) || "google".equals(providerName)){
//             DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
//             Map<String, Object> attributes = principal.getAttributes();
//             email = attributes.getOrDefault("email", "").toString();
//             String name = attributes.getOrDefault("name", "").toString();
//             if(providerName.equals("github")){
//                 username = attributes.getOrDefault("login","").toString();
//                 idAttributeKey = "id";
//             }
//             else if(providerName.equals("google")){
//                 username = email.split("@")[0];
//                 idAttributeKey="sub";
//             }else{
//                 username="";
//                 idAttributeKey="id";
//             }
            
//             try{
//                 UserDto user = userService.getUserByEmail(email);
//                 List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//                 user.getRoles().forEach( role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRole())));
// //                DefaultOAuth2User oAuth2User = new DefaultOAuth2User(simpleGrantedAuthorities, attributes, idAttributeKey);
// //                Authentication securityAuth = new OAuth2AuthenticationToken(oAuth2User, simpleGrantedAuthorities, providerName);
// //                SecurityContextHolder.getContext().setAuthentication(securityAuth);
//             } catch (Exception e) {
//                 User user = new User();
//                 user.setEmail(email);
//                 user.setUsername(username);
//                 try {
//                     userDetailsService.addUserByOAuth(user);
//                 } catch (ResourceNotFoundException ex) {
//                     throw new RuntimeException("Default role is not found");
//                 }
//                 List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//                 user.getRoles().forEach( role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRole())));
// //                DefaultOAuth2User oAuth2User = new DefaultOAuth2User(simpleGrantedAuthorities, attributes, idAttributeKey);
// //                Authentication securityAuth = new OAuth2AuthenticationToken(oAuth2User, simpleGrantedAuthorities, providerName);
// //                SecurityContextHolder.getContext().setAuthentication(securityAuth);
//             }
//         }

//         this.setAlwaysUseDefaultTargetUrl(true);

//         try {
//             newUser = userService.getUserByEmail(email);
//             String token = jwtTokenService.generateToken(newUser);
//             String refreshToken = jwtTokenService.generateRefreshToken(newUser);
//             if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//     			UserDetails userDetails = userDetailsService.loadUserByUsername(email);

//     			if(jwtService.validateToken(token, userDetails)) {
//     				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

//     				// Set additional details for the authentication token (optional)
//     				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//     				// Set the authentication in the SecurityContext
//     				SecurityContextHolder.getContext().setAuthentication(authToken);
//     			}
//     		}
//             String targetUrl = UriComponentsBuilder.fromUriString(frontendUrl +"/oauth2/redirect")
//                     .queryParam("token", token)
//                     .queryParam("refreshToken", refreshToken)
//                     .build().toUriString();
//             this.setDefaultTargetUrl(targetUrl);
//             super.onAuthenticationSuccess(request, response, authentication);
//         } catch (UserNotFoundException e) {
//             throw new RuntimeException(e);
//         }
//     }
// }


package com.gyanpath.security.config;

import com.gyanpath.security.dto.UserDto;
import com.gyanpath.security.entity.User;
import com.gyanpath.security.exception.ResourceNotFoundException;
import com.gyanpath.security.exception.UserNotFoundException;
import com.gyanpath.security.service.JwtService;
import com.gyanpath.security.service.JwtTokenService;
import com.gyanpath.security.service.impl.UserDetailsServiceImpl;
import com.gyanpath.security.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class OauthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String providerName = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

        if ("github".equals(providerName) || "google".equals(providerName)) {
            handleOAuthLogin(authentication, providerName);
        }

        this.setAlwaysUseDefaultTargetUrl(true);

        try {
            UserDto newUser = userService.getUserByEmail(getEmailFromAuthentication(authentication));
            String token = jwtTokenService.generateToken(newUser);
            String refreshToken = jwtTokenService.generateRefreshToken(newUser);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                authenticateUser(request, newUser.getEmail(), token);
            }
            String userId = String.valueOf(newUser.getUserId());


            String redirectFrontendUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                    .queryParam("token", token)
                    .queryParam("refreshToken", refreshToken)
                    .queryParam("email", newUser.getEmail())
                    .queryParam("userId", userId)
                    .queryParam("username", newUser.getUsername())
                    .build().toUriString();
            this.setDefaultTargetUrl(redirectFrontendUrl);
            super.onAuthenticationSuccess(request, response, authentication);
        } catch (UserNotFoundException e) {
            log.error("User not found during OAuth login", e);
            throw new RuntimeException("User not found during OAuth login", e);
        }
    }

    private void handleOAuthLogin(Authentication authentication, String providerName) {
        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        String email = attributes.getOrDefault("email", "").toString();
        String username = extractUsername(providerName, attributes, email);

        try {
            userService.getUserByEmail(email);
        } catch (UserNotFoundException e) {
            log.info("User not found, creating new user with email: {}", email);
            createUser(email, username);
        }
    }

    private String extractUsername(String providerName, Map<String, Object> attributes, String email) {
        if ("github".equals(providerName)) {
            return attributes.getOrDefault("login", "").toString();
        } else if ("google".equals(providerName)) {
            return email.split("@")[0];
        }
        return "";
    }

    private void createUser(String email, String username) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);

        try {
            userDetailsService.addUserByOAuth(user);
        } catch (ResourceNotFoundException ex) {
            log.error("Default role not found ", ex);
            throw new RuntimeException("Default role is not found", ex);
        }
    }

    private void authenticateUser(HttpServletRequest request, String email, String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (jwtService.validateToken(token, userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    private String getEmailFromAuthentication(Authentication authentication) {
        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
        return principal.getAttributes().getOrDefault("email", "").toString();
    }
}