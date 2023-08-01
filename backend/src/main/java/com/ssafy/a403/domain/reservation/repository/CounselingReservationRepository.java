package com.ssafy.a403.domain.reservation.repository;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselingReservationRepository extends JpaRepository<CounselingReservation, Long> {
}
