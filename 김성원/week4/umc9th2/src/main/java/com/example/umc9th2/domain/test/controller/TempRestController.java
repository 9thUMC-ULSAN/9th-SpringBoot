package com.example.umc9th2.domain.test.controller;


import com.example.umc9th2.domain.test.converter.TestConverter;
import com.example.umc9th2.domain.test.dto.res.TestResDTO;
import com.example.umc9th2.domain.test.service.query.TestQueryService;
import com.example.umc9th2.global.apiPayload.ApiResponse;
import com.example.umc9th2.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TestQueryService testQueryService;

    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ) {
        // 1) 예외 조건 체크
        testQueryService.checkFlag(flag);

        // 2) 예외 없을 때 정상 응답
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                TestConverter.toExceptionDTO("This is Test!")
        );
    }
}
