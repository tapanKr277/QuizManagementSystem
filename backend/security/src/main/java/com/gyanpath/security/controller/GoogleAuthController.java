package com.gyanpath.security.controller;


import com.gyanpath.security.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/google")
public class GoogleAuthController {

    // @Autowired
    // private GoogleAuthService googleAuthService;

    // @GetMapping("/callback")
    // public ResponseEntity<?> callBack(@RequestParam String code) throws Exception {
    //     try{
    //         return ResponseEntity.ok(googleAuthService.handleCallBack(code));
    //     }catch (Exception e){
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    //     }
    // }
}
