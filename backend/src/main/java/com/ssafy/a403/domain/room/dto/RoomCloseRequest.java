package com.ssafy.a403.domain.room.dto;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomCloseRequest {

    private String sessionId;

    private String recordingId;
}
