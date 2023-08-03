package com.ssafy.a403.domain.reservation.controller;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import com.ssafy.a403.domain.reservation.dto.ReservationRequest;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.service.CounselingReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
public class CounselingReservationController {

    private final CounselingReservationService counselingReservationService;

    @Autowired
    MemberRepository memberRepository;

    public CounselingReservationController(CounselingReservationService counselingReservationService) {
        this.counselingReservationService = counselingReservationService;
    }


    //예약 db에 저장
    @PostMapping("/reserve")
    public String reserve(@RequestBody ReservationRequest reservationRequest,
                          RedirectAttributes redirectAttributes){
        //임의로 회원 정보 저장
        Member member1 = new Member();
        member1.setId(1L);
        Member member2 = new Member();
        member2.setId(2L);
        Member member3 = new Member();
        member3.setId(3L);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        Long memberId = reservationRequest.getMemberId();
        Long counselorId = reservationRequest.getCounselorId();
        LocalDateTime reservationDate = reservationRequest.getReservationDate();

        Long reservationNo = counselingReservationService.reservation(memberId, counselorId, reservationDate);
        redirectAttributes.addFlashAttribute("reservationNo", reservationNo);

        return "redirect:/api/mypage";
    }

    // 예약 조회
    @GetMapping("/reservation_info/{memberId}")
    public List<CounselingReservation> getRezInfo(@PathVariable Long memberId){
        List<CounselingReservation> reservations = counselingReservationService.getReservation(memberId);
//        List<CounselingReservation> allReservations = counselingReservationService.getAllReservations();
        JSONArray jsonArray = new JSONArray();

        JSONArray resultArray = new JSONArray();
        System.out.println(reservations);


        // memberid, counselorid, 시간, 상태, 후기, 요약리포트, 상담방url 반환

//        System.out.println(reservations);
//        System.out.println("여기!!!!!!!!!!!!!!"+reservations.get(0).getReservationNo());

        return reservations;
    }




}
