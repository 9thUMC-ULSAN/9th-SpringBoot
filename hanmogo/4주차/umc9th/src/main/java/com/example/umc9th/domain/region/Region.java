package com.example.umc9th.domain.region;

import com.example.umc9th.domain.store.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Region { // BaseEntity 상속 불필요 (DDL에 타임스탬프 없음)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId; // DDL: int

    @Column(length = 255)
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Store> storeList = new ArrayList<>();
}