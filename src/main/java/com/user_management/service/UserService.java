package com.user_management.service;

import com.user_management.dto.AuthRequest;
import com.user_management.dto.AuthResponse;
import com.user_management.entity.User;
import com.user_management.exception.InvalidCredentialsException;
import com.user_management.repository.UserRepository;
import com.user_management.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public AuthResponse register(AuthRequest request) {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);

            String token = jwtUtil.generateToken(user.getUsername());
            return new AuthResponse(token);
        }

        public AuthResponse login(AuthRequest request) {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException("Invalid credentials");
            }

            String token = jwtUtil.generateToken(user.getUsername());
            return new AuthResponse(token);
        }
}
