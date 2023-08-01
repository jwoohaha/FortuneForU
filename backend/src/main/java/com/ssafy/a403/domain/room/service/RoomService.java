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

        CounselingReservation counselingReservation = CounselingReservation.builder()
                .reservationNo(reservationNo)
                .sessionId(sessionId)
                .build();

        return counselingReservationRepository.save(counselingReservation);

    }
}
