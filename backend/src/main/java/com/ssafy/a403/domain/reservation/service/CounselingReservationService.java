package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounselingReservationService {

    private final CounselingReservationRepository counselingReservationRepository;
    private final MemberRepository memberRepository;
    private final CounselingReservationRepository reservationRepository;

    //예약하기
//    public Long reservation(Long memberId, Long counselorId, LocalDateTime reservationDate) {
//        // (예외)가능한 시간이 아니야
//
//
//        Member member = memberRepository.findById(memberId).orElseThrow(
//                () -> {
//                    throw new IllegalArgumentException("존재하지 않는 회원입니다.");
//                }
//        );
//        Member counselor = memberRepository.findById(counselorId).orElseThrow(
//                () -> {
//                    throw new IllegalArgumentException("존재하지 않는 상담가입니다.");
//                }
//        );

//        builder 이름 설정
//        CounselingReservation counselingReservation = CounselingReservation.createReservation()
//                .member(member)
//                .counselor(counselor)
//                .reservationDateTime(reservationDate)
//                .reservationStatus(ReservationStatus.Waiting)
//                .build();

//        CounselingReservation savedReservation = counselingReservationRepository.save(counselingReservation);
//
//        return savedReservation.getReservationNo();
//    }

    // 일반회원 예약 조회
    public List<CounselingReservation> getReservation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        return reservationRepository.findByMember(member);
    }
}



