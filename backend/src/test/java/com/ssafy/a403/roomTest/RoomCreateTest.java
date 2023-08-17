//package com.ssafy.a403.roomTest;
//
//import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
//import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@SpringBootTest
//@Transactional
//public class RoomCreateTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(RoomCreateTest.class);
//
//    @Autowired
//    CounselingReservationRepository counselingReservationRepository;
//
//    @AfterEach
//    public void clean(){
//        counselingReservationRepository.deleteAll();
//    }
//
//    @Test
//    public void createSession(){
//        //given
//        Long reservationNo = 3l;
//        String sessionId = "sessionId";
//
//        CounselingReservation counselingReservation = CounselingReservation.builder()
//                .reservationNo(reservationNo)
//                .sessionId(sessionId)
//                .build();
//
//        counselingReservationRepository.save(counselingReservation);
//
//        Optional<CounselingReservation> counselingInfo = counselingReservationRepository.findById(reservationNo);
//
//        System.out.println(counselingInfo);
//    }
//
//    @Test
//    public void enterSession() throws Exception {
//        //given
////        Long reservationNo = 3l;
////
////        CounselingReservation counseling = counselingReservationRepository.findById(reservationNo).orElseThrow(
////                () -> new Exception()
////        );
////
////        String sessionId = counseling.getSessionId();
////
////        logger.info(sessionId);
//
//    }
//
//
//}
