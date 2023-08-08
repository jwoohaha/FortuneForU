package com.ssafy.a403.domain.reservation.dto;



import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ReviewResponse {

    private Long counselorId;
    private String memberName;
    private String counselorName;
    private String profileImage;
    private float ratingAvg;
    private BigDecimal reservationScore;
    private String review;

    public ReviewResponse from(CounselingReservation counselingReservation) {
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.counselorId = counselingReservation.getCounselor().getNo();
        reviewResponse.memberName = counselingReservation.getMember().getName();
        reviewResponse.counselorName = counselingReservation.getCounselor().getMember().getName();
        reviewResponse.review = counselingReservation.getReservationReview();
        reviewResponse.profileImage = counselingReservation.getCounselor().getMember().getProfileImage();
        reviewResponse.ratingAvg = counselingReservation.getCounselor().getRatingAvg();
        reviewResponse.reservationScore = counselingReservation.getReservationScore();

        return reviewResponse;

    }
}
