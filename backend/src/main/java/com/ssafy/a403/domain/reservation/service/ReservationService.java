package com.ssafy.a403.domain.reservation.service;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import com.ssafy.a403.global.config.security.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final CounselingReservation counselingReservation;
    private final UserRepository userRepository;

    @Transactional
    public Long counselingReservation(Long user, Long counselor, LocalDateTime reservationDateTime) {
        User user = userRepository.findOne(user)

    }


}
