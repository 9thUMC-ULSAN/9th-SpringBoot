package com.example.umc9th.domain.foodCategory;

import com.example.umc9th.domain.member.MemberPreference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodCategoryId;

    @Column(length = 20)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<MemberPreference> userPreferenceList = new ArrayList<>();
}