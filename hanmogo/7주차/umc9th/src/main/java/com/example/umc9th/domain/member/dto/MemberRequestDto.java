package com.example.umc9th.domain.member.dto;

import lombok.Getter;

public class MemberRequestDto {

    @Getter
    public static class ReviewDto {
        private Integer id;
        private String content;
        private Float score;
    }
}