package com.example.umc9th.domain.review;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.validation.annotation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public Page<ReviewResponseDto> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return reviewService.getFilteredReviews(storeId, rating, pageable);
    }

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "특정 회원이 작성한 리뷰 목록을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이지 번호"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @GetMapping("/members/{memberId}")
    public ApiResponse<ReviewResponseDto.MyReviewListDto> getMyReviews(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)") @RequestParam @ValidPage Integer page
    ) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(memberId, page));
    }
}