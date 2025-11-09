package com.example.localservice.repository;
import com.example.localservice.domain.Review;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 사용자의 memberId를 이용해 작성된 모든 리뷰를 최신순으로 조회
    // user_id는 Member 엔티티를 참조하므로, 필드명 'user'를 이용해 쿼리 생성
    List<Review> findAllByUser_MemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}