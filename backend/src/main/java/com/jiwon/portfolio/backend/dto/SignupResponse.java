package com.jiwon.portfolio.backend.dto;

import com.jiwon.portfolio.backend.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupResponse {

    private final Long id;

    private final String username;

    public static SignupResponse from(User user) {

        return new SignupResponse(
                user.getId(),
                user.getUsername()
        );
    }
}
