package com.gyanpath.apigateway.security;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {


    public static final List<String> openApiEndPoints = List.of(
            "/api/auth/login",
            "/api/auth/add-user",
            "/api/auth/contact-us",
            "/api/auth/validate-token",
            "/api/auth/forgot-password",
            "/api/auth/verify-otp",
            "/api/auth/resend-otp",
            "/api/auth/change-password",
            "/api/auth/email/sendMail",
            "/api/auth/verify-email",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndPoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
