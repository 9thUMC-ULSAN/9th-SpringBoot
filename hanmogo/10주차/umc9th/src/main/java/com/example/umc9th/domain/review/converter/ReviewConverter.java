package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.Review;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Page;

public class ReviewConverter {

    public static ReviewResponseDto.MyReviewDto toMyReviewDto(Review review) {
        return ReviewResponseDto.MyReviewDto.builder()
                .reviewId(review.getReviewId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDto.MyReviewListDto toMyReviewListDto(Page<Review> reviewPage) {
        return ReviewResponseDto.MyReviewListDto.builder()
                .reviewList(reviewPage.getContent().stream()
                        .map(ReviewConverter::toMyReviewDto)
                        .toList())
                .listSize(reviewPage.getContent().size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
