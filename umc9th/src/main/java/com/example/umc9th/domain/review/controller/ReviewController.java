package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.domain.review.service.ReviewService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewService reviewService;

//    @GetMapping("/reviews/search")
//    public List<Review> searchReview(
//            @RequestParam String query,
//            @RequestParam String type
//    ) {
//
//        // 서비스에게 요청
//        List<Review> result = reviewQueryService.searchReview(query, type);
//        return result;
//    }
    @GetMapping("/my")
    public List<Review> getMyReview(
        @RequestParam(required = false) String storeName,
        @RequestParam(required = false) Integer starRange
    ) {
    return reviewQueryService.getMyReviews(storeName, starRange);
    }

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewRequestDTO request
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.CREATED,
                reviewService.createReview(storeId, request)
        );
    }
}
