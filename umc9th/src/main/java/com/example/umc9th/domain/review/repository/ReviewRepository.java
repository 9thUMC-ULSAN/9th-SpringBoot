package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 가게(store.id)의 리뷰 목록
    List<Review> findByStore_Id(Long storeId);

    // 특정 회원이 작성한 리뷰 목록
    @Query("SELECT r FROM Review r JOIN r.store s WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByMember(@Param("memberId") Long memberId);

    // 특정 가게(store.id)
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.createdAt DESC")
    List<Review> findReviewsByStoreLatest(@Param("storeId") Long storeId);
}
