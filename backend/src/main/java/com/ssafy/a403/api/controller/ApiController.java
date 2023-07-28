package com.ssafy.a403.api.controller;

import com.ssafy.a403.api.response.RoomResponse;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init(){ this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET); }

    @PostMapping("/api/roomsession")
    public ResponseEntity<?> makeRoomSession()
            throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("---------------------방 만들기 시작----------------------");

        //랜덤 sessionId 생성
        String sessionId = UUID.randomUUID().toString();

        //session생성
        //properties의 customSessionId설정
        SessionProperties properties = new SessionProperties.Builder()
                                            .customSessionId(sessionId)
                                            .build();

        Session session = openVidu.createSession(properties);

        //방생성 예약DB에 저장 후 반환
        RoomResponse roomResponse = RoomResponse.builder()
                        .roomId(sessionId)
                        .build();

        return new ResponseEntity<>(roomResponse, HttpStatus.OK);
    }

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





}
