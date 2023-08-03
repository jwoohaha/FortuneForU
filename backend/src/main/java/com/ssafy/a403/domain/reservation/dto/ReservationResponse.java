package com.ssafy.a403.domain.reservation.dto;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ReservationResponse {

    private Long reservationNo;

    private Member member;

    private Member counselor;

    private LocalDateTime reservationDateTime;

    private ReservationStatus reservationStatus;

    private String reservationURL;

    private String reservationReview;

    private String reservationReport;


    public static ReservationResponse from(CounselingReservation counselingReservation) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.reservationNo = counselingReservation.getReservationNo();
        reservationResponse.member = counselingReservation.getMember();
        reservationResponse.counselor = counselingReservation.getCounselor();
        reservationResponse.reservationDateTime = counselingReservation.getReservationDateTime();
        reservationResponse.reservationStatus = counselingReservation.getReservationStatus();
        reservationResponse.reservationURL = counselingReservation.getReservationURL();
        reservationResponse.reservationReview = counselingReservation.getReservationReview();
        reservationResponse.reservationReport = counselingReservation.getReservationReport();

        return reservationResponse;
    }


}
