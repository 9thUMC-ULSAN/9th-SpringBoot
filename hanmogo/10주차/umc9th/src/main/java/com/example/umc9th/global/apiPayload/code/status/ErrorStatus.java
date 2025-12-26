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
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001", "해당하는 음식 카테고리가 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4001", "해당하는 지역이 없습니다."),
    MISSION_ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4002", "이미 도전 중인 미션입니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4003", "해당하는 회원 미션이 없습니다."),
    MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION4004", "진행 중인 미션이 아닙니다."),
    ;

    // 2. 필드 정의
    private final HttpStatus status;
    private final String code;
    private final String message;
}
