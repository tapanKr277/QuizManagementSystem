package com.gyanpath.security.controller;

import com.gyanpath.security.dto.*;
import com.gyanpath.security.entity.User;
import com.gyanpath.security.exception.QuizNotFoundException;
import com.gyanpath.security.exception.ResourceNotFoundException;
import com.gyanpath.security.exception.UserNotFoundException;
import com.gyanpath.security.mapper.UserMapper;
import com.gyanpath.security.service.PasswordResetOtpService;
import com.gyanpath.security.service.RoleService;
import com.gyanpath.security.service.UserService;
import com.gyanpath.security.service.VerificationTokenService;
import com.gyanpath.security.service.client.QuestionServiceClient;
import com.gyanpath.security.service.client.QuizServiceClient;
import com.gyanpath.security.service.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/admin")
@Tag(name = "Admin Controller", description = "Admin Controller to perform Admin operation")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final UserService userService;
    private final RoleService roleService;
    private final VerificationTokenService verificationTokenService;
    private final PasswordResetOtpService passwordResetOtpService;
    private final QuizServiceClient quizServiceClient;
    private final QuestionServiceClient questionServiceClient;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AdminController(UserService userService,
                           RoleService roleService,
                           VerificationTokenService verificationTokenService,
                           PasswordResetOtpService passwordResetOtpService,
                           QuizServiceClient quizServiceClient,
                           QuestionServiceClient questionServiceClient,
                           UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.roleService = roleService;
        this.verificationTokenService = verificationTokenService;
        this.passwordResetOtpService = passwordResetOtpService;
        this.quizServiceClient = quizServiceClient;
        this.questionServiceClient = questionServiceClient;
        this.userDetailsService = userDetailsService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-user-list")
    public ResponseEntity<List<UserDto>> getAllUserList(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUserList());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-role-list")
    public ResponseEntity<List<RoleDto>> getAllRoleList(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-token-list")
    public ResponseEntity<List<VerificationTokenDto>> getAllVerificationTokenList(){
        return ResponseEntity.status(HttpStatus.OK).body(verificationTokenService.getAllTokenList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-otp-list")
    public ResponseEntity<List<PasswordResetOtpDto>> getAllOtpList(){
        return ResponseEntity.status(HttpStatus.OK).body(passwordResetOtpService.getAllOtpList());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-quiz-list")
    public ResponseEntity<List<QuizDto>> getAllQuizList(){
        return quizServiceClient.getAllQuiz();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete-quiz")
    public ResponseEntity<Map<String, String>> deleteQuiz(@RequestParam(name = "quiz_id") Short quizId) throws QuizNotFoundException {
        return quizServiceClient.deleteQuiz(quizId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-all-question-list")
    public ResponseEntity<List<QuestionDto>> getAllQuestionList(){
        return questionServiceClient.getAllQuestion();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/update-user")
    public ResponseEntity<UserDto> updateUserData(@RequestBody UserDto userDto) throws UserNotFoundException {
        System.out.println(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserData(userDto));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete-user")
    @Transactional
    public ResponseEntity<?> deleteUser(@RequestParam Short userId) {
        try{
            quizServiceClient.deleteQuizAttempt(userId);
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-user")
    @Transactional
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDto userDto) throws ResourceNotFoundException {
        User user = UserMapper.userDtoToUser(userDto);
        userDetailsService.addUser(user);
        UserMapper.userToUserDto(user);
        Map<String, String> map = new HashMap<>();
        map.put("success", "User created successfully");
        return ResponseEntity.created(URI.create("/created")).body(map);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-quiz")
    @Transactional
    public ResponseEntity<Map<String, String>> createQuiz(@RequestBody QuizDto quizDto) throws QuizNotFoundException {
        try{
            quizServiceClient.createQuiz(quizDto);
            Map<String, String> response = new HashMap<>();
            response.put("success", "Quiz Created Successfully");
            return ResponseEntity.created(URI.create("/create-quiz")).body(response);
        }
        catch (Exception e){
            throw new QuizNotFoundException(e.getMessage());
        }
    }
}
