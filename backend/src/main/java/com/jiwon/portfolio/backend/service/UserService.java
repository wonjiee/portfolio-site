package com.jiwon.portfolio.backend.service;

import com.jiwon.portfolio.backend.dto.SignupRequest;
import com.jiwon.portfolio.backend.entity.User;
import com.jiwon.portfolio.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public User signup(SignupRequest request) {

        if (repository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        return repository.save(user);
    }

    public User login(
            String username,
            String password) {

        User user = repository
                .findByUsername(username)
                .orElseThrow();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호 오류");
        }

        return user;
    }
}
