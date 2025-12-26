package com.example.umc9th.domain.auth.exception;

import com.example.umc9th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class AuthException extends GeneralException {

    private final AuthErrorCode errorCode;

    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }
}
