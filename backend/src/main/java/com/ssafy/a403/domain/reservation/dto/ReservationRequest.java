package com.ssafy.a403.domain.reservation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {


    private Long memberId;
    private Long counselorId;
    private LocalDateTime reservationDate;
    private String reservationReview;




    public static ReservationRequest requestData(Long memberId, Long counselorId, LocalDateTime reservationDate, String reservationReview) {

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.memberId = memberId;
        reservationRequest.counselorId = counselorId;
        reservationRequest.reservationDate = reservationDate;
        reservationRequest.reservationReview = reservationReview;

        return reservationRequest;
    }

}