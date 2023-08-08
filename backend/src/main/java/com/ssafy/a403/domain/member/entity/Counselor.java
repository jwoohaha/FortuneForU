package com.ssafy.a403.domain.member.entity;

import com.ssafy.a403.domain.model.CounselorType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselor_no")
    private Long no;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_no")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CounselorType counselorType;

    private String major;

    private String intro;

    private String address;

    private String phone;

    @ColumnDefault("0")
    private int reviewCnt;

    @ColumnDefault("0.00")
    private float ratingAvg;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;


    @Builder
    public Counselor(Long no, Member member, CounselorType counselorType, String major, String intro,
                     String address, String phone, LocalDateTime startDate, LocalDateTime endDatetime) {
        this.no = no;
        this.member = member;
        this.counselorType = counselorType;
        this.major = major;
        this.intro = intro;
        this.address = address;
        this.phone = phone;
        this.startDatetime = startDate;
        this.endDatetime = endDatetime;
    }

    public void updateCounselorReview(float rating) {
        this.ratingAvg = Math.round((this.ratingAvg * this.reviewCnt + rating) / (reviewCnt + 1));
        this.reviewCnt += 1;
    }
}
