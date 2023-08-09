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

    private int Hour;

    private int Minute;

    public AvailableDateTime from(ReservationResponse reservation){
        AvailableDateTime availableDateTime = new AvailableDateTime();
        availableDateTime.Hour = reservation.getReservationDateTime().getHour();
        availableDateTime.Minute = reservation.getReservationDateTime().getMinute();

        return availableDateTime;
    }


}
