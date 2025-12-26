package com.example.umc9th.domain.agreement;

import com.example.umc9th.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ServiceAgreement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String term;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean options;

    @Builder.Default
    @OneToMany(mappedBy = "serviceAgreement", cascade = CascadeType.ALL)
    private List<MemberAgreement> memberAgreementList = new ArrayList<>();
}