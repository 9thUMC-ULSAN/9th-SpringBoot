package com.example.umc9th2.domain.review.service;

import com.example.umc9th2.domain.review.entity.Review;
import com.example.umc9th2.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getMyReviews(Long memberId, Long storeId, Integer starGroup) {
        return reviewRepository.findMyReviews(memberId, storeId, starGroup);
    }
}
