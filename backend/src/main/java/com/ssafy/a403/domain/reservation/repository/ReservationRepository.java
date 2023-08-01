package com.ssafy.a403.domain.reservation.repository;

import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

    private final EntityManager em;

    public void save(CounselingReservation counselingReservation) {
        em.persist(counselingReservation);
    }

    public CounselingReservation findOne(Long id) {
        return em.find(CounselingReservation.class, id);
    }

}
