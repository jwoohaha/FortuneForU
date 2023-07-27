package com.ssafy.a403.api.response;

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
