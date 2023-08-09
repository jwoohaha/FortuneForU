package com.ssafy.a403.domain.reservation.dto;


import lombok.Data;

@Data
public class ReviewRequest {

    private Long memberId;
    private Long counselorId;
    private String reservationReview;

    public static ReviewRequest reviewRequest(Long memberId, Long counselorId, String reservationReview){
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.memberId = memberId;
        reviewRequest.counselorId = counselorId;
        reviewRequest.reservationReview = reservationReview;

        return reviewRequest;
    }
}
