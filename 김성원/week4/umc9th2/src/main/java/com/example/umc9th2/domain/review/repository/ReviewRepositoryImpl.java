package com.example.umc9th2.domain.review.repository;

import com.example.umc9th2.domain.review.entity.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.umc9th2.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> findMyReviews(Long memberId, Long storeId, Integer starGroup) {

        return queryFactory
                .selectFrom(review)
                .where(
                        review.member.id.eq(memberId),
                        storeEq(storeId),
                        starGroupEq(starGroup)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    private BooleanExpression storeEq(Long storeId) {
        return storeId != null ? review.store.id.eq(storeId) : null;
    }

    private BooleanExpression starGroupEq(Integer starGroup) {
        if (starGroup == null) return null;

        double min = starGroup;
        double max = starGroup + 1.0;

        return review.star.between(min, max);
    }
}
