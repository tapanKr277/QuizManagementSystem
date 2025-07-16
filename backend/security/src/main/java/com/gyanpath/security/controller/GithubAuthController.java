package com.gyanpath.security.controller;


import com.gyanpath.security.service.GitHubAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/github")
public class GithubAuthController {

    // @Autowired
    // private GitHubAuthService gitHubAuthService;

    // @GetMapping("/callback")
    // public ResponseEntity<?> callBack(@RequestParam String code){
    //     try{
    //         return ResponseEntity.ok(gitHubAuthService.handleCallBack(code));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    //     }
    // }
}
