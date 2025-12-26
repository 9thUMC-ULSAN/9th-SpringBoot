package com.example.umc9th.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    // JpaRepository의 기본 메서드 + ReviewRepositoryCustom의 메서드 모두 사용 가능
}