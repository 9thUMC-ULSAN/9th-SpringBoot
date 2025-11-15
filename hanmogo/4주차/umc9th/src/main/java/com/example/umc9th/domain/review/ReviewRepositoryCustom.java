package com.example.umc9th.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {

    // 동적 쿼리 메서드 선언
    // Pageable을 추가하여 "최근 작성" 순으로 "페이징" 처리
    Page<Review> findReviewsByFilters(Long storeId, Integer rating, Pageable pageable);

}
