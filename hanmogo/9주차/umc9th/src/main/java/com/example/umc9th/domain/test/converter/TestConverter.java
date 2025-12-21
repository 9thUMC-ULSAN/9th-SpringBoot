package com.example.umc9th.domain.test.converter;

import com.example.umc9th.domain.test.dto.TestResDto;

public class TestConverter {

    // 객체 -> DTO
    public static TestResDto.Testing toTestingDto(
            String testing
    ) {
        return TestResDto.Testing.builder()
                .testString(testing)
                .build();
    }
    // 객체 -> DTO
    public static TestResDto.Exception toExceptionDTO(
            String testing
    ){
        return TestResDto.Exception.builder()
                .testString(testing)
                .build();
    }
}