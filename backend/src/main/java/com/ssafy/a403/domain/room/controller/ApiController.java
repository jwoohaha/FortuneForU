package com.ssafy.a403.domain.room.controller;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.room.dto.RoomRequest;
import com.ssafy.a403.domain.room.dto.RoomResponse;
import com.ssafy.a403.domain.room.service.RoomService;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
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

    private final OpenVidu openVidu;

    private final RoomService roomService;

    //방 생성
    @PostMapping("/api/roomsession")
    public ResponseEntity<?> makeRoomSession(@RequestBody RoomRequest roomRequest, HttpServletRequest request)
            throws OpenViduJavaClientException, OpenViduHttpException {
        System.out.println("넘어옴");
        log.info("---------------------방 만들기 시작----------------------");

        //랜덤 sessionId 생성
        String sessionId = UUID.randomUUID().toString();

        //session생성
        //properties의 customSessionId설정
        SessionProperties properties = new SessionProperties.Builder()
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
        log.info("session Id : " + sessionId);

        //sessionId로 session 가져오기
        Session session = openVidu.getActiveSession(sessionId);

        //session이 존재하지 않는다면 NOT FOUND 리턴
        if (session == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //연결과 토큰 만들기
        ConnectionProperties properties = new ConnectionProperties.Builder().build();
        Connection connection = session.createConnection(properties);

        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);

    }


    //방 삭제하기
    @DeleteMapping("/api/sessions/{sessionId}")
    public ResponseEntity<?> deleteRoom(@PathVariable("sessionId") String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("---------------------방 삭제-----------------------");

        CounselingReservation counselingReservation = roomService.findBySessionId(sessionId);

//        String recordingId = counselingReservation.getReservationRecorded();

        //sessionID DB상에서 삭제
        Long reservationNo = counselingReservation.getReservationNo();

//        CounselingReservation updateCounselingReservation = RoomService.updateSessionId(reservationNo);

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

    //녹화 시작
    @PostMapping(value = "/api/recording/{sessionId}")
    public ResponseEntity<?> startRecording(@PathVariable String sessionId){

        log.info("------------------------녹화 시작-------------------");

        RecordingProperties recordingProperties = new RecordingProperties.Builder()
                .hasAudio(true)
                .hasVideo(false)
                .build();

        CounselingReservation counselingReservation = roomService.findBySessionId(sessionId);

        try {
            Recording recording = openVidu.startRecording(sessionId, recordingProperties);
            log.info("start recording : " + recording.getUrl());
            return new ResponseEntity<>(recording, HttpStatus.OK);
        } catch (OpenViduHttpException | OpenViduJavaClientException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
    // 녹화 중지
    @PostMapping(value = "/api/recording/stop/{sessionId}")
    public ResponseEntity<?> stopRecording(@PathVariable String sessionId){

        log.info("------------------------녹화 중지-------------------");

        String recordingId = sessionId;

        try {
            Recording recording = this.openVidu.stopRecording(recordingId); // 안에 정보가 들어간다.
//            this.sessionRecordings.remove(recording.getSessionId()); // 만들지 않았으니 없앨것도 없고
            return new ResponseEntity<>(recording, HttpStatus.OK);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }


}
