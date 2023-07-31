package com.ssafy.a403.domain.reservation.entity;

import com.ssafy.a403.domain.user.entity.User;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "counselor_id")
    private User counselor;

    @Column(name="rez_time")
    private LocalDateTime reservationDateTime;

    @Column(name="rez_status")
    private String reservationStatus;

    @Column(name="rez_url")
    private String reservationURL;

    @Column(name="rez_review_status")
    private Boolean reservationReviewStatus;

    @Column(name="rez_review")
    private String reservationReview;

    @Column(name="rez_report")
    private String reservationReport;

    @Column(name="rez_recorded")
    private String reservationRecorded;


    @Builder
    public CounselingReservation(User user, User counselor, LocalDateTime reservationDateTime, String reservationStatus,
                                 String reservationURL, Boolean reservationReviewStatus, String reservationReview, String reservationReport, String reservationRecorded) {
        this.user = user;
        this.counselor = counselor;
        this.reservationDateTime = reservationDateTime;
        this.reservationStatus = reservationStatus;
        this.reservationURL = reservationURL;
        this.reservationReviewStatus = reservationReviewStatus;
        this.reservationReview = reservationReview;
        this.reservationReport = reservationReport;
        this.reservationRecorded = reservationRecorded;
    }

}
