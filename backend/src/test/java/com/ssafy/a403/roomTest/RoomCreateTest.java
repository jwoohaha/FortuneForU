//package com.ssafy.a403.roomTest;
//
//import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
//import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RoomCreateTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(RoomCreateTest.class);
//
//    @Autowired
//    CounselingReservationRepository counselingReservationRepository;
//
//    @After
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
