package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewRequestDTO;
import com.example.umc9th.domain.review.dto.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public ReviewResponseDTO createReview(Long storeId, ReviewRequestDTO request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        // 🔥 하드코딩된 유저
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(
                request.getContent(),
                request.getStar(),
                member,
                store
        );

        Review savedReview = reviewRepository.save(review);
        return ReviewConverter.toReviewResponseDTO(savedReview);
    }
}
