package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.CounselorRepository;
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
    private final CounselorRepository counselorRepository;


    //예약하기
    public Long reservation(Long memberId, Long counselorId, LocalDateTime reservationDate) {
//        // 예약 가능한 시간인지 확인?

//        테스트용!!!!!!!!!!!!!!!1
        Member member1 = new Member();
        member1.setId(1L);
        Member member2 = new Member();
        member2.setId(2L);
        Member member3 = new Member();
        member3.setId(3L);
        Counselor counselor1 = new Counselor();
        counselor1.setId(1L);
        Counselor counselor2 = new Counselor();
        counselor1.setId(2L);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        counselorRepository.save(counselor1);
        counselorRepository.save(counselor2);

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("존재하지 않는 회원입니다.");
                }
        );
        Counselor counselor = counselorRepository.findById(counselorId).orElseThrow(
                () -> {
                    throw new IllegalArgumentException("존재하지 않는 상담가입니다.");
                }
        );

        CounselingReservation counselingReservation = CounselingReservation.builder()
                .member(member)
                .counselor(counselor)
                .reservationDateTime(reservationDate)
                .reservationStatus(ReservationStatus.Waiting)
                .build();

        CounselingReservation savedReservation = counselingReservationRepository.save(counselingReservation);

        return savedReservation.getReservationNo();
    }

    // 일반회원 예약 조회
    public List<CounselingReservation> getReservation(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        return counselingReservationRepository.findByMember(member);
    }

    public List<CounselingReservation> getCoReservation(Long counselorId) {
        Counselor member = counselorRepository.findById(counselorId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        return counselingReservationRepository.findByCounselor(member);
    }

    //취소
    public void cancelReservation(Long reservationNo) {
       CounselingReservation counselingReservation = counselingReservationRepository.findById(reservationNo).get();
       System.out.println(counselingReservation);
        counselingReservation.cancel();

    }

}



