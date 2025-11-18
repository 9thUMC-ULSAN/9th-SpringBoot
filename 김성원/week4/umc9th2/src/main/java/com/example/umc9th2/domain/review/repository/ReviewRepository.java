package com.example.umc9th2.domain.review.repository;

import com.example.umc9th2.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewRepositoryCustom{

    // 특정 가게에 등록된 리뷰 목록 조회
    @Query("SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.createdAt DESC")
    List<Review> findByStoreId(@Param("storeId") Long storeId);

    // 특정 회원이 작성한 리뷰 목록 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    List<Review> findByMemberId(@Param("memberId") Long memberId);
}
