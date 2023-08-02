package com.ssafy.a403.domain.room.controller;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.room.dto.RoomRequest;
import com.ssafy.a403.domain.room.dto.RoomResponse;
import com.ssafy.a403.domain.room.service.RoomService;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;


    private OpenVidu openVidu;

    private final RoomService roomService;

    @PostConstruct
    public void init(){ this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET); }

    //방 생성
    @PostMapping("/api/roomsession")
    public ResponseEntity<?> makeRoomSession(@RequestBody RoomRequest roomRequest, HttpServletRequest request)
            throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("---------------------방 만들기 시작----------------------");

        //랜덤 sessionId 생성
        String sessionId = UUID.randomUUID().toString();

        //생성시 recording 추가
//        RecordingProperties recordingProperties = new RecordingProperties.Builder()
//                .outputMode(Recording.OutputMode.COMPOSED)
//                .resolution("640*480")
//                .frameRate(24)
//                .hasAudio(true)
//                .hasVideo(false)
//                .build();

        //session생성
        //properties의 customSessionId설정
        SessionProperties properties = new SessionProperties.Builder()
//                                            .recordingMode(RecordingMode.MANUAL)
//                                            .defaultRecordingProperties(recordingProperties)
                                            .customSessionId(sessionId)
                                            .build();

        Session session = openVidu.createSession(properties);

        log.info("sessionId : " + session.getSessionId());

        //방 생성 예약DB에 저장
        CounselingReservation counselingReservation = roomService.saveRoom(roomRequest, sessionId);

        if (session == null || counselingReservation.getSessionId() == null) {
            log.info("방 생성 실패");
        }

        //sessionId를 response로 반환
        RoomResponse roomResponse = RoomResponse.builder()
                        .sessionId(sessionId)
                        .build();

        return new ResponseEntity<>(roomResponse, HttpStatus.OK);
    }

    //방 입장하기
    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<?> createConnection(@PathVariable("sessionId") String sessionId)
            throws OpenViduJavaClientException, OpenViduHttpException{

        log.info("--------------------방 접속 시작---------------------------");
        log.info("url session Id : " + sessionId);

        //sessionId로 session 가져오기
        Session session = openVidu.getActiveSession(sessionId);

        //session이 존재하지 않는다면 NOT FOUND 리턴
        if (session == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //연결과 토큰 만들기
        ConnectionProperties properties = new ConnectionProperties.Builder().build();
        Connection connection = session.createConnection(properties);

        return new ResponseEntity<>(connection, HttpStatus.OK);

    }


    //방 삭제하기
    @DeleteMapping("/api/sessions/{sessionId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("sessionId") String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("---------------------방 삭제-----------------------");

        CounselingReservation counselingReservation = roomService.findBySessionId(sessionId);

//        String recordingId = counselingReservation.getReservationRecorded();

        //sessionID DB상에서 삭제
        Long reservationNo = counselingReservation.getReservationNo();

//        RoomService.deleteSessionIdByReservationNo(reservationNo);

        //Session close
        Session session = openVidu.getActiveSession(sessionId);

        if(session != null) {
            openVidu.getActiveSession(sessionId).close();
            log.info("방 삭제 완료");
        }

        //녹화 종료 및 저장
//        Recording recording = openVidu.stopRecording(recordingId);

        return new ResponseEntity<>(HttpStatus.OK);

    }



}
