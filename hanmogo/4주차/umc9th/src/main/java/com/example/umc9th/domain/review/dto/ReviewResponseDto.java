package com.example.umc9th.domain.review.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ReviewResponseDto {

    private Long reviewId;
    private String memberName;
    private Float score;
    private String content;
    private LocalDate createdAt;


}