package com.example.localservice.domain; // 👈 추가

// import com.example.localservice.domain.Review; // 👈 이 줄 꼭 추가
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "review_photo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPhotoId;

    @Column(nullable = false, length = 500)
    private String photoUrl;

    // **연관 관계 설정: Review (N:1)**
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
}