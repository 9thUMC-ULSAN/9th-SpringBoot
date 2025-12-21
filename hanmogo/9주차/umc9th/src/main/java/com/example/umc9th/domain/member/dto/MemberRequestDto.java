package com.example.umc9th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberRequestDto {

    @Builder
    @Getter
    public static class ReviewDto {
        private Long storeId;
        private String content;
        private Float score;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDto {
        private Long id;
        private String name;
        private LocalDate birthdate;
        private String address;
        private String detailAddress;
        private String gender;
        private LocalDateTime createAt;
        private List<Long> preferCategory;
    }
}
