package com.ssafy.a403.domain.room.controller;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.room.dto.RoomCloseRequest;
import com.ssafy.a403.domain.room.dto.RoomRequest;
import com.ssafy.a403.domain.room.dto.RoomResponse;
import com.ssafy.a403.domain.room.service.RoomService;
import com.ssafy.a403.global.config.security.LoginUser;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
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
    public ResponseEntity<?> makeRoomSession(@RequestBody RoomRequest roomRequest, @AuthenticationPrincipal LoginUser loginUser)
            throws OpenViduJavaClientException, OpenViduHttpException {
        log.info("---------------------방 만들기 시작----------------------");
        log.info("roomRequest reservationNo: " + roomRequest.getReservationNo());

        //email @ 앞부분 추출
        String email = loginUser.getMember().getEmail();
        int idx = email.indexOf("@");
        String memberId = email.substring(0, idx); 
        
        //아이디 + 랜덤 sessionId 생성
        String sessionId = memberId + UUID.randomUUID().toString();

        //session생성
        //properties의 customSessionId설정
        SessionProperties properties = new SessionProperties.Builder()
                .customSessionId(sessionId)
                .build();

        Session session = openVidu.createSession(properties);

        //방 생성 예약DB에 저장
        Optional<CounselingReservation> counselingReservation = roomService.saveRoom(roomRequest, sessionId);

        if (session == null || counselingReservation.get().getSessionId() == null) {
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

        //TODO JWT로 userinfo 불러와서 userId와 sessionId 비교해서 oepnviudRole 부여
        //연결과 토큰 만들기
        ConnectionProperties properties = new ConnectionProperties.Builder().build();
        Connection connection = session.createConnection(properties);

        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);

    }


    //방 삭제하기
    @PutMapping("/api/sessions")
    public ResponseEntity<?> closeRoom(@RequestBody RoomCloseRequest roomCloseRequest) throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("---------------------방 삭제-----------------------");

        String sessionId = roomCloseRequest.getSessionId();
        String recordingId = roomCloseRequest.getRecordingId();

        CounselingReservation counselingReservation = roomService.findBySessionId(sessionId);

        Long reservationNo = counselingReservation.getReservationNo();

        //todo openvidurole 받아와서 상담가만 방삭제 + 녹화종료 가능하게 변경, 방 퇴장 따로 생성해야하는지 체크

        //녹화 종료 및 저장
        Recording recording = openVidu.stopRecording(recordingId);

        String recordingUrl = recording.getUrl();
        log.info("recordingUrl : " + recordingUrl);

        //sessionID null, recordingUrl update
        if(roomService.updateSessionIdAndRecordingUrl(reservationNo, recordingUrl)){
            log.info("수정 완료");
        };

        //Session close
        Session session = openVidu.getActiveSession(sessionId);

        if(session != null) {
            openVidu.getActiveSession(sessionId).close();
            log.info("방 삭제 완료");
        }

        return ResponseEntity.noContent().build();

    }

    //todo 녹화시작 따로 만들지 않고 방 생성할때 연동되는지 확인
    //녹화 시작
    @PostMapping(value = "/api/recording/{sessionId}")
    public ResponseEntity<?> startRecording(@PathVariable String sessionId){

        log.info("------------------------녹화 시작-------------------");

        //녹화 환경 설정
        RecordingProperties recordingProperties = new RecordingProperties.Builder()
                .hasAudio(true)
                .hasVideo(false)
                .build();

        try {
            Recording recording = openVidu.startRecording(sessionId, recordingProperties);
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
            log.info("stop recording : " + recording.getUrl());
//            this.sessionRecordings.remove(recording.getSessionId()); // 만들지 않았으니 없앨것도 없고
            return new ResponseEntity<>(recording, HttpStatus.OK);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }
    // 녹화 중지
   


}
