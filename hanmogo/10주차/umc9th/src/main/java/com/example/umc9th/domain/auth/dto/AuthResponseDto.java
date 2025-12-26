package com.example.umc9th.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AuthResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class SignUpResponse {
        private Long memberId;
        private String email;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResponse {
        private Long memberId;
        private String email;
        private String name;
        private String accessToken;  // JWT 방식에서 사용
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TokenResponse {
        private String accessToken;
        private String refreshToken;
    }
}
