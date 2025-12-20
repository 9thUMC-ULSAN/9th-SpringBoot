package com.example.umc9th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponseDTO {

    private Long reviewId;
    private String content;
    private Float star;
    private Long storeId;
    private LocalDateTime createdAt;
}
