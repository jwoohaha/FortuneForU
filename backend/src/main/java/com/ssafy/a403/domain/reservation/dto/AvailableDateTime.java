package com.ssafy.a403.domain.reservation.dto;


import com.ssafy.a403.domain.member.entity.Counselor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
public class AvailableDateTime {

    private String startTime;

    private String endTime;

    private List<LocalDateTime> reservationList = new ArrayList<>();



    public AvailableDateTime from(Counselor counselor, List<LocalDateTime> reservations){
        AvailableDateTime availableDateTime = new AvailableDateTime();
        availableDateTime.startTime = counselor.getStartTime();
        availableDateTime.endTime = counselor.getEndTime();
        availableDateTime.reservationList = reservations;

        return availableDateTime;
    }

}
