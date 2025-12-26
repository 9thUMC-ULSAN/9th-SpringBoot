package com.example.umc9th.domain.mission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionStatus {

    CHALLENGING("진행중"),
    COMPLETE("완료"),
    ;

    private final String description;
}