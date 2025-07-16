package com.gyanpath.security.config;

import com.gyanpath.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initialize(UserService userService) {
        return args -> userService.initializeRolesAndAdmin();
    }
}
