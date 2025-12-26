package com.example.umc9th.domain.review;

import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    // 반환 타입을 Page<Review> -> Page<ReviewResponseDTO>로 변경
    Page<ReviewResponseDto> getFilteredReviews(Long storeId, Integer rating, Pageable pageable);

    // 내가 작성한 리뷰 목록 조회
    ReviewResponseDto.MyReviewListDto getMyReviews(Long memberId, Integer page);
}