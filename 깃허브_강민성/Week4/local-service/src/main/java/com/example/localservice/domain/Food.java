package com.example.localservice.domain;
import jakarta.persistence.*;
import lombok.*;
import com.example.localservice.type.FoodType;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // DB DDL에 따라 NOT NULL
    private FoodType name; // 컬럼명 'name'이 Enum 타입 자체를 저장

    // ... (나머지 필드 없음)
}