package com.example.umc9th.domain.review;


import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Page<ReviewResponseDto> getFilteredReviews(Long storeId, Integer rating, Pageable pageable) {

        // Repository를 호출하여 엔티티 페이지를 가져옴
        Page<Review> reviewPage = reviewRepository.findReviewsByFilters(storeId, rating, pageable);

        return reviewPage.map(review ->
                ReviewResponseDto.builder()
                        .reviewId(review.getReviewId())
                        .memberName(review.getMember().getName())
                        .score(review.getScore().floatValue())
                        .content(review.getBody())
                        .createdAt(review.getCreatedAt().toLocalDate())
                        .build()
        );
    }

    @Override
    public ReviewResponseDto.MyReviewListDto getMyReviews(Long memberId, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<Review> reviewPage = reviewRepository.findMyReviews(memberId, pageable);
        return ReviewConverter.toMyReviewListDto(reviewPage);
    }
}