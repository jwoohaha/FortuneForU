package com.ssafy.a403.domain.room.service;

import com.ssafy.a403.domain.model.ReservationStatus;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.domain.reservation.repository.CounselingReservationRepository;
import com.ssafy.a403.domain.room.dto.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final CounselingReservationRepository counselingReservationRepository;

    @Transactional
    public Optional<CounselingReservation> saveRoom(RoomRequest roomRequest, String sessionId){

        counselingReservationRepository.saveRoom(sessionId, ReservationStatus.Proceeding, roomRequest.getReservationNo());

        return counselingReservationRepository.findById(roomRequest.getReservationNo());

    }

    public CounselingReservation findBySessionId(String sessionId) {

        CounselingReservation counselingReservation = counselingReservationRepository.findBySessionId(sessionId);

        return counselingReservation;

    }

    @Transactional
    public boolean updateSessionIdAndRecordingUrl(Long reservationNo, String recordingUrl) {

        counselingReservationRepository.saveCloseRoom(ReservationStatus.End, recordingUrl, reservationNo);

        return true;

    }
}