package com.example.umc9th.domain.review.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ReviewResponseDto {

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createdAt
    ){}

    @Builder
    public record MyReviewListDto(
            List<MyReviewDto> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyReviewDto(
            Long reviewId,
            String storeName,
            Integer score,
            String body,
            LocalDateTime createdAt
    ){}

    private Long reviewId;
    private String memberName;
    private Float score;
    private String content;
    private LocalDate createdAt;
}