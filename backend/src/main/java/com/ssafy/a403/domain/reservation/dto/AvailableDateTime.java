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

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    private List<LocalDateTime> reservationList = new ArrayList<>();



    public AvailableDateTime from(Counselor counselor, List<LocalDateTime> reservations){
        AvailableDateTime availableDateTime = new AvailableDateTime();
        availableDateTime.startDatetime = counselor.getStartDatetime();
        availableDateTime.endDatetime = counselor.getEndDatetime();
        availableDateTime.reservationList = reservations;

        return availableDateTime;
    }

}
