package com.ssafy.a403.domain.reservation.entity;


import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.ReservationStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@ToString
public class CounselingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "counselor_no")
    private Member counselor;

    @Column(name="rez_time")
    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name="rez_status")
    private ReservationStatus reservationStatus;

    @Column(name="rez_url")
    private String reservationURL;


    @Column(name="rez_review")
    private String reservationReview;

    @Column(name="rez_report")
    private String reservationReport;

    @Column(name="rez_recorded")
    private String reservationRecorded;



    @Builder(builderMethodName = "createReservation")
    public CounselingReservation(Member member, Member counselor, LocalDateTime reservationDateTime, ReservationStatus reservationStatus,
                                 String reservationURL, String reservationReview, String reservationReport, String reservationRecorded) {
        this.member = member;

        this.counselor = counselor;
        this.reservationDateTime = reservationDateTime;
        this.reservationStatus = reservationStatus;
        this.reservationURL = reservationURL;
        this.reservationReview = reservationReview;
        this.reservationReport = reservationReport;
        this.reservationRecorded = reservationRecorded;
    }


    public void cancel() {
        if (reservationDateTime.isAfter(LocalDateTime.now())) {
            this.reservationStatus = ReservationStatus.Cancel;
        } else {
            throw new IllegalArgumentException("취소 가능한 날짜가 지났습니다.");
        }
    }

}
