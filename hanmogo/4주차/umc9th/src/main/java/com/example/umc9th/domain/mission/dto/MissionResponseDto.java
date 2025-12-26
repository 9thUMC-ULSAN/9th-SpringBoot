package com.example.umc9th.domain.mission.dto;

import com.example.umc9th.domain.mission.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMissionResultDto {
        private Long missionId;
        private String title;
        private String missionSpec;
        private Integer reward;
        private LocalDate deadline;
        private String storeName;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeMissionResultDto {
        private Long memberMissionId;
        private Long missionId;
        private String missionTitle;
        private String storeName;
        private String status;
        private LocalDateTime createdAt;
    }

    @Builder
    public record StoreMissionListDto(
            List<StoreMissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record StoreMissionDto(
            Long missionId,
            String title,
            String conditional,
            Integer point,
            LocalDate deadline
    ){}

    @Builder
    public record MyMissionListDto(
            List<MyMissionDto> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record MyMissionDto(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String title,
            String conditional,
            Integer point,
            LocalDate deadline,
            MissionStatus status,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MissionCompleteDto(
            Long memberMissionId,
            MissionStatus status,
            LocalDateTime updatedAt
    ){}
}
