package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    // 일반적인 응답
    OK(HttpStatus.OK, "COMMON200", "성공입니다."),


    CREATED(HttpStatus.CREATED, "COMMON201", "생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}