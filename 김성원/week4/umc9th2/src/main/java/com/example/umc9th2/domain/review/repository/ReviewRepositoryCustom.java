package com.example.umc9th2.domain.review.repository;

import com.example.umc9th2.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> findMyReviews(Long memberId, Long storeId, Integer starGroup);
}
