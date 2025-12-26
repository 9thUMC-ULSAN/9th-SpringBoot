package com.example.umc9th.domain.auth.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "AUTH201", "회원가입이 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "AUTH200", "로그인이 완료되었습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "AUTH200", "로그아웃이 완료되었습니다."),
    TOKEN_REFRESH_SUCCESS(HttpStatus.OK, "AUTH200", "토큰이 갱신되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
