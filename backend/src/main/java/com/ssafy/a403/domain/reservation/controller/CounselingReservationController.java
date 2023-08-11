package com.ssafy.a403.domain.reservation.controller;

import com.ssafy.a403.domain.reservation.dto.*;
import com.ssafy.a403.domain.reservation.service.CounselingReservationService;
import com.ssafy.a403.global.config.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reservations")
public class CounselingReservationController {

    private final CounselingReservationService counselingReservationService;

    //예약 가능 시간 조회
    @GetMapping("/availabledate/{counselorNo}/{date}")
    public List<AvailableDateTime> availableDate(@PathVariable Long counselorNo, @PathVariable String date) {
        return counselingReservationService.availableDateTime(counselorNo, date);
    }



    //예약 db에 저장
    @PostMapping("/reserve")
    public ResponseEntity<String> reserve(@AuthenticationPrincipal LoginUser loginUser,
                          @RequestBody ReservationRequest reservationRequest){

        try {
            Long memberId = loginUser.getMember().getNo();
            Long counselorId = reservationRequest.getCounselorId();
            String reservationType = reservationRequest.getReservationType();
            LocalDateTime reservationDate = reservationRequest.getReservationDate();

            Long reservationNo = counselingReservationService.reservation(memberId, counselorId, reservationType, reservationDate);
            return ResponseEntity.ok("예약 성공");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    // 일반회원 id로 예약 조회
    @GetMapping("/member_rez_info")
    public List<ReservationResponse> getRezInfo(@AuthenticationPrincipal LoginUser loginUser){
        Long memberId = loginUser.getMember().getNo();
        return  counselingReservationService.getReservation(memberId);

    }


    // 상담가 id로 예약 조회
    @GetMapping("/counselor_rez_info/{date}")
    public List<ReservationResponse> getCounselorRezInfo(@AuthenticationPrincipal LoginUser loginUser, @PathVariable String date){
        Long counselorId = loginUser.getMember().getCounselor().getNo();
        return counselingReservationService.getCoReservation(counselorId, date);

    }


    // 예약 번호로 예약 조회
    @GetMapping("/reservations/{reservationNo}")
    public ReservationResponse getReservations(@PathVariable Long reservationNo) {

        return counselingReservationService.checkReservation(reservationNo);

    }


    //예약 취소
    @PatchMapping("/cancel/{reservationNo}")
    public String cancel(@PathVariable Long reservationNo) {
        counselingReservationService.cancelReservation(reservationNo);
        return "취소";
    }


    //후기 작성
    @PostMapping("/{reservationNo}/save_review")
    public String saveReview(@RequestBody ReviewRequest reviewRequest,
                             @PathVariable Long reservationNo){
        Long memberId = reviewRequest.getMemberId();
        String review = reviewRequest.getReservationReview();
        float rez_score = reviewRequest.getReservationScore();
        counselingReservationService.postReview(reservationNo, memberId, review, rez_score);

        return "ok";
    }


    // 후기 삭제
    @PatchMapping("/{reservationNo}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reservationNo) {
        try {
            counselingReservationService.deleteReview(reservationNo);
            return ResponseEntity.ok("리뷰 삭제 성공했습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // 일반회원 후기 조회
    @GetMapping("/reviews")
    public List<ReviewResponse> getReview(@AuthenticationPrincipal LoginUser loginUser) {
        Long memberId = loginUser.getMember().getNo();
        return counselingReservationService.getReview(memberId);

    }


    //상담가 후기 조회(타인이 상담사 후기 조회)
    @GetMapping("/{counselorId}/co_reviews")
    public List<ReviewResponse> getCoReview(@PathVariable Long counselorId) {

        return counselingReservationService.getCoReview(counselorId);
    }

    // 상담사 후기 조회(본인)
    @GetMapping("/co_reviews")
    public List<ReviewResponse> getCoReview(@AuthenticationPrincipal LoginUser loginUser) {
        Long counselorId = loginUser.getMember().getCounselor().getNo();
        return counselingReservationService.getCoReview(counselorId);
    }
}
