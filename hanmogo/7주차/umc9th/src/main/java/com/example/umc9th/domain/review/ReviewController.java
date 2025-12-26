package com.example.umc9th.domain.review;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping

    public Page<ReviewResponseDto> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        // 서비스 호출 후 DTO 페이지를 그냥 반환
        return reviewService.getFilteredReviews(storeId, rating, pageable);
    }
}