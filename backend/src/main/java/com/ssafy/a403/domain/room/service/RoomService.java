package com.ssafy.a403.domain.room.service;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
import com.ssafy.a403.domain.room.dto.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final CounselingReservationRepository counselingReservationRepository;

    public CounselingReservation saveRoom(RoomRequest roomRequest, String sessionId){
        Long reservationNo = roomRequest.getReservationNo();
//        Long reservationNo = 1l;

        CounselingReservation counselingReservation = CounselingReservation.builder()
                .reservationNo(reservationNo)
                .sessionId(sessionId)
                .build();

        return counselingReservationRepository.save(counselingReservation);

    }

    public CounselingReservation findBySessionId(String sessionId) {

        CounselingReservation counselingReservation = counselingReservationRepository.findBySessionId(sessionId);

        return counselingReservation;

    }


    public void updateRecording(CounselingReservation counselingReservation, String recordingUrl) {

        Long reservationNo = counselingReservation.getReservationNo();
    }

    public boolean updateSessionIdAndRecordingUrl(Long reservationNo, String recordingUrl) {

        CounselingReservation counselingReservation = CounselingReservation.builder()
                .reservationNo(reservationNo)
                .reservationRecorded(recordingUrl)
//                .reservationStatus()
                .build();

        counselingReservationRepository.save(counselingReservation);

        return true;

    }
}