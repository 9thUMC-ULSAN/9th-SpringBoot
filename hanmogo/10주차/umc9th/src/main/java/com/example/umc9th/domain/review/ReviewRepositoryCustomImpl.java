package com.example.umc9th.domain.review;

// Q-Type 클래스 import (static으로)
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.umc9th.domain.review.QReview.review;
import static com.example.umc9th.domain.store.QStore.store;
import static com.example.umc9th.domain.member.QMember.member;


@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;



    @Override
    public Page<Review> findReviewsByFilters(Long storeId, Integer rating, Pageable pageable) {

        // 1. 데이터 조회 쿼리
        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.store, store) // storeId로 필터링하기 위해 join
                .where(
                        storeIdEq(storeId), //  가게 ID
                        ratingEq(rating)    //  별점
                )
                .orderBy(review.createdAt.desc()) // "최근 작성한" 순서
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. 카운트 쿼리 (페이지네이션 용)
        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(
                        storeIdEq(storeId),
                        ratingEq(rating)
                );

        // PageableExecutionUtils.getPage()를 사용해 Page 객체 생성
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    //  가게 ID가 일치하는가
    private BooleanExpression storeIdEq(Long storeId) {
        // storeId가 null이면(입력 안 받으면) null을 리턴 (where 절에서 무시됨)
        return storeId != null ? review.store.storeId.eq(storeId) : null;
    }

    //  별점이 일치하는가
    private BooleanExpression ratingEq(Integer rating) {
        if (rating == null) {
            return null; // null이면 조건 무시
        }

        if (rating == 5) {
            // 5점은 5만 해당
            return review.score.eq(5);
        } else if (rating > 0 && rating < 5) {
            // "4점대" (rating=4) -> 4 <= score < 5
            // "3점대" (rating=3) -> 3 <= score < 4
            return review.score.goe(rating).and(review.score.lt(rating + 1));
        }

        return null; // 0이나 5 초과 등 잘못된 값은 무시
    }

    @Override
    public Page<Review> findMyReviews(Long memberId, Pageable pageable) {
        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(review.member, member).fetchJoin()
                .where(review.member.memberId.eq(memberId))
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(review.member.memberId.eq(memberId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}