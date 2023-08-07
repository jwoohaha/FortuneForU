package com.ssafy.a403.domain.reservation.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {

//    private Long reservationNo;
    private Long memberId;
    private Long counselorId;
    private LocalDateTime reservationDate;
    private String reservationReview;
//    private String sessionId;


    public static ReservationRequest requestData(Long reservationNo, Long memberId, Long counselorId, LocalDateTime reservationDate, String reservationReview,
                                                 String sessionId) {

        ReservationRequest reservationRequest = new ReservationRequest();
//        reservationRequest.reservationNo = reservationNo;
        reservationRequest.memberId = memberId;
        reservationRequest.counselorId = counselorId;
        reservationRequest.reservationDate = reservationDate;
        reservationRequest.reservationReview = reservationReview;
//        reservationRequest.sessionId = sessionId;

        return reservationRequest;
    }

}