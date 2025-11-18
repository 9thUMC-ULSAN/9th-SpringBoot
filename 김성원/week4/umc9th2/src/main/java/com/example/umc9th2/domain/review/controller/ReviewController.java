package com.example.umc9th2.domain.review.controller;

import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/member/{memberId}")
    public List<Review> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer starGroup
    ) {
        return reviewService.getMyReviews(memberId, storeId, starGroup);
    }
}
