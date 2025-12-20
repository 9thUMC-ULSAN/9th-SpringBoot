package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QLocation;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

//    public List<Review> searchReview(String query, String type) {
//
//        // Q클래스 정의
//        QReview review = QReview.review;
//        QLocation location = QLocation.location; // ★ location 조인을 위한 Q클래스 필요!
//
//        // BooleanBuilder 정의
//        BooleanBuilder builder = new BooleanBuilder();
//
//        // 동적 쿼리: 검색 조건
//        if (type.equals("location")) {
//            builder.and(review.store.location.name.contains(query));
//        }
//
//        if (type.equals("star")) {
//            builder.and(review.star.goe(Float.parseFloat(query)));
//        }
//
//        if (type.equals("both")) {
//
//            // & 기준 변환
//            String firstQuery = query.split("&")[0];
//            String secondQuery = query.split("&")[1];
//
//            // 동적 쿼리
//            builder.and(review.store.location.name.contains(firstQuery));
//            builder.and(review.star.goe(Float.parseFloat(secondQuery)));
//        }
//
//        // Repository 사용 & 결과 매핑
//        List<Review> reviewList = reviewRepository.searchReview(builder);
//
//        // 리턴
//        return reviewList;
//    }
public List<Review> getMyReviews(String storeName, Integer starRange) {

    QReview review = QReview.review;
    QStore store = QStore.store;

    BooleanBuilder builder = new BooleanBuilder();

    // ✔ 가게명 필터링
    if (storeName != null && !storeName.isEmpty()) {
        builder.and(store.name.contains(storeName));
    }

    // ✔ 별점 필터링 (예: starRange = 4 → 4.0 ~ 4.9)
    if (starRange != null) {
        Float min = starRange.floatValue();
        Float max = starRange.floatValue() + 0.9f;

        builder.and(review.star.between(min, max));
    }

    // Repository(QueryDSL 구현체) 호출
    return reviewRepository.searchReview(builder);
}
}
