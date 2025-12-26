package com.example.umc9th.domain.review.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewRequestDto {

    private Long reviewId;
    private String content;

}
