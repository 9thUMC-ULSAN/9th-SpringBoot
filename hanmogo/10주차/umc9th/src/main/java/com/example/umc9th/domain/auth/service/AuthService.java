package com.example.umc9th.domain.auth.service;

import com.example.umc9th.domain.auth.dto.AuthRequestDto;
import com.example.umc9th.domain.auth.dto.AuthResponseDto;

public interface AuthService {

    AuthResponseDto.SignUpResponse signUp(AuthRequestDto.SignUpRequest request);

    AuthResponseDto.LoginResponse login(AuthRequestDto.LoginRequest request);
}
