package com.ssafy.a403.domain.reservation.service;


import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.CounselorFormRepository;
import com.ssafy.a403.domain.member.repository.MemberRepository;

import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = false)
public class CounselingReservationServiceTest {
    @Autowired CounselingReservationService counselingReservationService;
    @Autowired
    CounselingReservationRepository counselingReservationRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CounselorFormRepository counselor;


    @Before
    public void setup() {
        Member member1 = new Member();
        member1.setId(1L);
        Member member2 = new Member();
        member2.setId(2L);
        Member member3 = new Member();
        member3.setId(3L);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

    }

    // 에약 저장 확인
//    @Test
//    @Transactional
    @Before
    public void reservationTest() throws Exception {
        //given
        Long memberId = 1L;
        Long counselorId = 2L;
        Long member3 = 3L;
        LocalDateTime reservationDate = LocalDateTime.of(2023, 8, 1, 10, 0);

        //when
        Long reservationNo = counselingReservationService.reservation(memberId, counselorId, reservationDate);
        //then
        Long reservationNo1 = counselingReservationService.reservation(memberId, member3, reservationDate);

    }
    @Test
    @Transactional
    public void getRezTest() throws Exception {
        Long memberId = 1L;
        List<CounselingReservation> result = counselingReservationService.getReservation(memberId);
        result.forEach(r -> System.out.println(r.toString()));
//        System.out.println(result);
    }







}