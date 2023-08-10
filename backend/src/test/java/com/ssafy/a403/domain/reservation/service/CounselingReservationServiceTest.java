//package com.ssafy.a403.domain.reservation.service;
//
//import com.ssafy.a403.domain.member.entity.Counselor;
//import com.ssafy.a403.domain.member.entity.Member;
//import com.ssafy.a403.domain.member.repository.CounselorRepository;
//import com.ssafy.a403.domain.member.repository.MemberRepository;
//import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//
//@Slf4j
//@SpringBootTest
////@Rollback(value = false)
//public class CounselingReservationServiceTest {
//    @Autowired CounselingReservationService counselingReservationService;
//    @Autowired CounselingReservationRepository counselingReservationRepository;
//    @Autowired MemberRepository memberRepository;
//    @Autowired CounselorRepository counselorRepository;
//
//
//    // 에약 저장 확인
////    @Test
////    @Transactional
//    public void setup() {
//        Member member1 = Member.builder()
//                .no(1L)
//                .name("John Doe")
//                .email("john@example.com")
//                .build();
//
//        Member member2 = Member.builder()
//                .no(2L)
//                .name("Jane Smith")
//                .email("jane@example.com")
//                .build();
//
//        Member member3 = Member.builder()
//                .no(3L)
//                .name("sudo wane")
//                .email("jane@example.com")
//                .build();
//
//        memberRepository.saveAll(Arrays.asList(member1, member2, member3));
//
//        // 더미 데이터 생성 및 저장
//        Counselor counselor1 = Counselor.builder()
//                .no(1L)
//                .member(member2)
//                .build();
//
//        counselorRepository.saveAll(Arrays.asList(counselor1));
//
//    }
//
//    public void reservationTest() throws Exception {
//        //given
//        Long member1 = 1L;
//        Long counselor1 = 1L;
//        Long member3 = 3L;
//        LocalDateTime reservationDate1 = LocalDateTime.of(2023, 8, 10, 10, 0);
//        LocalDateTime reservationDate2 = LocalDateTime.of(2023, 8, 1, 10, 0);
//
//        //when
//        Long reservationNo1 = counselingReservationService.reservation(member1, counselor1, reservationDate1);
//        Long reservationNo2 = counselingReservationService.reservation(member3, counselor1, reservationDate2);
//
//
//    }
////    @Test
////    @Transactional
////    public void getRezTest() throws Exception {
////        Long memberId = 1L;
////        List<CounselingReservation> result = counselingReservationService.getReservation(memberId);
////    }
//
//    @Test
//    @Transactional
//    public void cancelTest() throws Exception {
//
//        Long reservationNo = 1L;
//
//        counselingReservationService.cancelReservation(reservationNo);
//
//
//    }
//
//}