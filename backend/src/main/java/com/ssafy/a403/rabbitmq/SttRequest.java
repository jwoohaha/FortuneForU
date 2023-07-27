package com.ssafy.a403.rabbitmq;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SttRequest {
    private String reservationId;
    private String audioFilePath;

    @Builder
    public SttRequest(String reservationId, String audioFilePath) {
        this.reservationId = reservationId;
        this.audioFilePath = audioFilePath;
    }
}
