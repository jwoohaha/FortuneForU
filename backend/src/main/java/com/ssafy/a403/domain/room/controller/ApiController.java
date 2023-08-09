package com.ssafy.a403.domain.room.controller;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.room.dto.RoomRequest;
import com.ssafy.a403.domain.room.dto.RoomResponse;
import com.ssafy.a403.domain.room.service.RoomService;
import com.ssafy.a403.global.advice.CustomException;
import com.ssafy.a403.global.config.security.LoginUser;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
            throw new CustomException(HttpStatus.BAD_REQUEST, "방 생성에 실패하였습니다.");
        }

        //sessionId를 response로 반환
        RoomResponse roomResponse = RoomResponse.builder()
                        .sessionId(sessionId)
                        .build();

        return new ResponseEntity<>(roomResponse, HttpStatus.OK);
    }

    //방 입장하기
    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<?> createConnection(@PathVariable("sessionId") String sessionId, @AuthenticationPrincipal LoginUser loginUser)
            throws OpenViduJavaClientException, OpenViduHttpException{

        log.info("--------------------방 접속 시작---------------------------");
        log.info("session Id : " + sessionId);

        //sessionId로 session 가져오기
        Session session = openVidu.getActiveSession(sessionId);

        String email = loginUser.getMember().getEmail();
        int idx = email.indexOf("@");
        String memberId = email.substring(0, idx);

        //session이 존재하지 않는다면 NOT FOUND 리턴
        if (session == null){
            throw new CustomException(HttpStatus.NOT_FOUND, "방 정보가 존재하지 않습니다.");
        }

        //연결과 토큰 만들기
        ConnectionProperties properties = new ConnectionProperties.Builder().role(OpenViduRole.PUBLISHER).build();
        Connection connection = session.createConnection(properties);

        return new ResponseEntity<>(connection, HttpStatus.OK);

    }


    //방 삭제하기
    @PutMapping("/api/sessions/{sessionId}")
    public ResponseEntity<?> closeRoom(@PathVariable("sessionId") String sessionId, @AuthenticationPrincipal LoginUser loginUser) throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("---------------------방 삭제-----------------------");

        String recordingId = sessionId;

        CounselingReservation counselingReservation = roomService.findBySessionId(sessionId);

        Long reservationNo = counselingReservation.getReservationNo();

        String email = loginUser.getMember().getEmail();
        int idx = email.indexOf("@");
        String memberId = email.substring(0, idx);

        //방 생성자만 삭제할 수 있게 설정
        if(!sessionId.startsWith(memberId)){
            throw new CustomException(HttpStatus.FORBIDDEN, "상담방을 삭제할 권한이 없습니다.");
        }

        //녹화 종료 및 저장
        Recording recording = openVidu.stopRecording(recordingId);

        String recordingUrl = recording.getUrl();
        log.info("recordingUrl : " + recordingUrl);

        //sessionID null, recordingUrl update
        if(roomService.updateSessionIdAndRecordingUrl(reservationNo, recordingUrl)){
            log.info("수정 완료");
        }else{
            throw new CustomException(HttpStatus.BAD_REQUEST, "방 삭제, 녹화 저장 실패");
        };

        //Session close
        Session session = openVidu.getActiveSession(sessionId);

        if(session != null) {
            openVidu.getActiveSession(sessionId).close();
            log.info("방 삭제 완료");
        }

        return ResponseEntity.noContent().build();

    }

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

}
