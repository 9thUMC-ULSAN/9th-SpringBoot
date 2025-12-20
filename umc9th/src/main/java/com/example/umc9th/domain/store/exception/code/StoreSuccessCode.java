package com.example.umc9th.domain.store.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode {
    FOUND(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 상점을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
