package com.jiwon.portfolio.backend.controller;

import com.jiwon.portfolio.backend.dto.LoginRequest;
import com.jiwon.portfolio.backend.dto.SignupRequest;
import com.jiwon.portfolio.backend.dto.SignupResponse;
import com.jiwon.portfolio.backend.entity.User;
import com.jiwon.portfolio.backend.jwt.JwtUtil;
import com.jiwon.portfolio.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public SignupResponse signup(
            @RequestBody SignupRequest request) {

        User user = service.signup(request);

        return SignupResponse.from(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody LoginRequest request) {

        User user = service.login(
                request.getUsername(),
                request.getPassword()
        );

        String token =
                jwtUtil.generateToken(
                        user.getUsername()
                );

        return Map.of(
                "token",
                token
        );
    }
}
