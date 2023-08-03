package com.ssafy.a403.domain.reservation.dto;


import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {

    private Long memberId;
    private Long counselorId;
    private LocalDateTime reservationDate;

    public static ReservationRequest requestData(Long memberId, Long counselorId, LocalDateTime reservationDate) {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.memberId = memberId;
        reservationRequest.counselorId = counselorId;
        reservationRequest.reservationDate = reservationDate;

        return reservationRequest;
    }

}