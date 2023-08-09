package com.ssafy.a403.domain.reservation.dto;


import lombok.Data;

@Data
public class ReviewRequest {

    private Long memberId;
    private Long counselorId;
    private String reservationReview;
    private float reservationScore;

    public static ReviewRequest reviewRequest(Long memberId, Long counselorId, String reservationReview, float reservationScore){
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.memberId = memberId;
        reviewRequest.counselorId = counselorId;
        reviewRequest.reservationReview = reservationReview;
        reviewRequest.reservationScore = reservationScore;

        return reviewRequest;
    }
}
