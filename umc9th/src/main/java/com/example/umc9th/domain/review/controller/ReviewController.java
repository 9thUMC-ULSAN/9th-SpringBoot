package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

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
}
