package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends CrudRepository<Reply, Long> {
    // 특정 리뷰의 답글 목록
    List<Reply> findByReview_Id(Long reviewId);

    // 리뷰 ID 기준 오름차순 정렬
    @Query("SELECT rp FROM Reply rp JOIN rp.review r WHERE r.id = :reviewId ORDER BY rp.id ASC")
    List<Reply> findRepliesByReviewId(@Param("reviewId") Long reviewId);
}
