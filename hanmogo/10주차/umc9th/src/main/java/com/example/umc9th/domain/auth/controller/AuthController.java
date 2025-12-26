package com.example.umc9th.domain.auth.controller;

import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;
import com.example.umc9th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umc9th.domain.auth.service.AuthService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    public ApiResponse<AuthResponseDto.SignUpResponse> signUp(
            @Valid @RequestBody AuthRequestDto.SignUpRequest request) {
        AuthResponseDto.SignUpResponse response = authService.signUp(request);
        return ApiResponse.onSuccess(AuthSuccessCode.SIGNUP_SUCCESS, response);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
    public ApiResponse<AuthResponseDto.LoginResponse> login(
            @Valid @RequestBody AuthRequestDto.LoginRequest request) {
        AuthResponseDto.LoginResponse response = authService.login(request);
        return ApiResponse.onSuccess(AuthSuccessCode.LOGIN_SUCCESS, response);
    }
}
