package com.ssafy.a403.domain.room.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoomResponse {

    String roomId;

    @Builder
    public RoomResponse(String roomId) {
        this.roomId = roomId;
    }
}
