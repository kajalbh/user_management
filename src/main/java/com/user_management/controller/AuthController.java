package com.user_management.controller;

import com.user_management.dto.AuthRequest;
import com.user_management.dto.AuthResponse;
import com.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        private UserService userService;

        @PostMapping("/register")
        public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
            return ResponseEntity.ok(userService.register(request));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
            return ResponseEntity.ok(userService.login(request));
        }

}
