package com.ssafy.a403.domain.reservation.dto;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ReservationResponse {

    private Long reservationNo;

    private Long memberId;

    private Long counselorId;

    private LocalDateTime reservationDateTime;

    private ReservationStatus reservationStatus;

    private String sessionId;

    private String reservationReview;

    private String reservationReport;


    public static ReservationResponse from(CounselingReservation counselingReservation) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.reservationNo = counselingReservation.getReservationNo();
        reservationResponse.memberId = counselingReservation.getMember().getNo();
        reservationResponse.counselorId = counselingReservation.getCounselor().getNo();
        reservationResponse.reservationDateTime = counselingReservation.getReservationDateTime();
        reservationResponse.reservationStatus = counselingReservation.getReservationStatus();
        reservationResponse.sessionId = counselingReservation.getSessionId();
        reservationResponse.reservationReview = counselingReservation.getReservationReview();
        reservationResponse.reservationReport = counselingReservation.getReservationReport();

        return reservationResponse;
    }


}
