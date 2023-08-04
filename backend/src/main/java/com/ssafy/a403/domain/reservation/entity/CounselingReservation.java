package com.ssafy.a403.domain.reservation.entity;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.ReportStatus;
import com.ssafy.a403.domain.model.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
public class CounselingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "counselor_no")
    private Counselor counselor;

    @Column(name="rez_time")
    private LocalDateTime reservationDateTime;


    @Column(name="rez_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Column(name="rez_url")
    private String sessionId;

    @Column(name="rez_review")
    private String reservationReview;

    @Column(name="rez_report")
    private String reservationReport;

    @Column(name="rez_report_status")
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;

    @Column(name="rez_recorded")
    private String reservationRecorded;


    @Builder
    public CounselingReservation(Long reservationNo, Member member, Counselor counselor, LocalDateTime reservationDateTime, ReservationStatus reservationStatus,
                                 String sessionId, String reservationReview, String reservationReport, ReportStatus reportStatus, String reservationRecorded) {
        this.reservationNo = reservationNo;
        this.member = member;
        this.counselor = counselor;
        this.reservationDateTime = reservationDateTime;
        this.reservationStatus = reservationStatus;
        this.sessionId = sessionId;
        this.reservationReview = reservationReview;
        this.reservationReport = reservationReport;
        this.reportStatus = reportStatus;
        this.reservationRecorded = reservationRecorded;
    }


    public void cancel() {
        if (reservationDateTime.isAfter(LocalDateTime.now())) {
            reservationStatus = ReservationStatus.Cancel;
        } else {
            throw new IllegalArgumentException("취소 가능한 날짜가 지났습니다.");
        }
    }
}
