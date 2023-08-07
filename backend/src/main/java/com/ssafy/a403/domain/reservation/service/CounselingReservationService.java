package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.CounselorRepository;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.dto.ReservationResponse;
import com.ssafy.a403.domain.reservation.dto.ReviewResponse;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselingReservationService {

    private final CounselingReservationRepository counselingReservationRepository;
    private final MemberRepository memberRepository;
    private final CounselorRepository counselorRepository;


    //예약하기
    public Long reservation(Long memberId, Long counselorId, LocalDateTime reservationDate) {
        Member member1 = Member.builder()
                .no(1L)
                .name("John Doe")
                .email("john@example.com")
                .build();

        Member member2 = Member.builder()
                .no(2L)
                .name("Jane Smith")
                .email("jane@example.com")
                .build();

        Member member3 = Member.builder()
                .no(3L)
                .name("sudo wane")
                .email("jane@example.com")
                .build();

        Member member4 = Member.builder()
                .no(4L)
                .name("kelly smith")
                .email("smithe@example.com")
                .build();

        memberRepository.saveAll(Arrays.asList(member1, member2, member3, member4));

        // 더미 데이터 생성 및 저장
        Counselor counselor1 = Counselor.builder()
                .no(1L)
                .member(member1)
                .build();

        Counselor counselor2 = Counselor.builder()
                .no(2L)
                .member(member2)
                .build();
        counselorRepository.saveAll(Arrays.asList(counselor1, counselor2));


//        // 예약 가능한 시간인지 확인?

        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);

        CounselingReservation counselingReservation = CounselingReservation.builder()
                .member(member)
                .counselor(counselor)
                .reservationDateTime(reservationDate)
                .reservationStatus(ReservationStatus.Waiting)
                .build();

        CounselingReservation savedReservation = counselingReservationRepository.save(counselingReservation);

        return savedReservation.getReservationNo();
    }


    // 예약 리스트
    public List<ReservationResponse> reservationList(List<CounselingReservation> reservations){
        List<ReservationResponse> reservationResponses = new ArrayList<>();
        for (CounselingReservation reservation : reservations) {
            reservationResponses.add(new ReservationResponse().from(reservation));
        }
        return reservationResponses;
    }


    // 일반회원 예약 조회
    public List<ReservationResponse> getReservation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservations = counselingReservationRepository.findByMember(member);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        reservations.sort(Comparator.comparing(CounselingReservation::getReservationDateTime).reversed());

        return reservationList(reservations);
    }


    // 상담가 예약 조회
    public List<ReservationResponse> getCoReservation(Long counselorId) {
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);
        List<CounselingReservation> reservations = counselingReservationRepository.findByCounselor(counselor);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        reservations.sort(Comparator.comparing(CounselingReservation::getReservationDateTime).reversed());

        return reservationList(reservations);
    }


    // 예약 번호로 예약 조회
    public ReservationResponse checkReservation(Long reservationNo) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);
        return new ReservationResponse().from(counselingReservation);
    }



    //예약 취소
    @Transactional
    public void cancelReservation(Long reservationNo) {
       CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);

       counselingReservation.cancel();
    }




    // 후기 작성
    @Transactional
    public void postReview(Long reservationNo, Long memberId, String review) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);

        if (!counselingReservation.checkEmpty()) {
            throw new IllegalArgumentException("Review already written");
        }
        if (!counselingReservation.checkMemberId(memberId)) {
            throw new IllegalArgumentException("Member does not match");
        }
//        if (!counselingReservation.checkStatus()) {
//            throw new IllegalArgumentException("Counseling session is not yet completed");
//        }
        counselingReservation.saveReview(review);
        counselingReservationRepository.save(counselingReservation);
    }



    // 후기 삭제
    @Transactional
    public void deleteReview(Long reservationNo) {
        CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).orElseThrow(EntityNotFoundException::new);

        if (counselingReservation != null) {
            counselingReservation.deleteReview();
        }
    }


    // 후기 리스트
    public List<ReviewResponse> reviewList(List<CounselingReservation> reservations){
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for ( CounselingReservation reservation : reservations) {
            if (reservation.getReservationReview() != null){
                reviewResponses.add(new ReviewResponse().from(reservation));
            }
        }
        return reviewResponses;
    }



    // 일반회원 후기 조회
    public List<ReviewResponse> getReview(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);

        List<CounselingReservation> reservations = counselingReservationRepository.findByMember(member);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewList(reservations);
    }



    // 상담사 후기 조회
    public List<ReviewResponse> getCoReview(Long counselorId){
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(EntityNotFoundException::new);

        List<CounselingReservation> reservations = counselingReservationRepository.findByCounselor(counselor);
        if (reservations.isEmpty()) {
            return Collections.emptyList();
        }
        return reviewList(reservations);
    }



}



