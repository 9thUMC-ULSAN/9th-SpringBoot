package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewConverter {

    public static Review toReview(
            String content,
            Float star,
            Member member,
            Store store
    ) {
        return Review.builder()
                .content(content)
                .star(star)
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDTO toReviewResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .storeId(review.getStore().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
