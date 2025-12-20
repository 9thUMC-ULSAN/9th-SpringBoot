package com.example.umc9th.domain.mission.entity;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "conditional")
    private String conditional;

    @Column(name = "point")
    private Integer point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    // 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
