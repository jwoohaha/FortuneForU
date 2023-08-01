package com.ssafy.a403.domain.reservation.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Getter
@Entity
@NoArgsConstructor
public class CounselingSchedule {

    @Id
    @JoinColumn(name="member_no")
    private int memberNo;

    private String mon;

    private String tue;

    private String wed;

    private String thu;

    private String fri;

    private String sat;

    private String sun;

    @Builder
    public CounselingSchedule(String mon, String tue, String wed, String thu, String fri, String sat, String sun){
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
    }

}

