package com.example.umc9th.global.apiPayload.code.status;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 기존에 있던 COMMON 관련 에러들...
    temp_error(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "임시 에러"),

    // 1. 에러 코드 정의
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "해당하는 가게가 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당하는 미션이 없습니다."),
    // 필요한 에러 코드 계속 추가...
    ;

    // 2. 필드 정의
    private final HttpStatus status;
    private final String code;
    private final String message;
}