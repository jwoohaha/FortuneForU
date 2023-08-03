package com.ssafy.a403.domain.reservation.repository;
import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.reservation.entity.CounselingReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselingReservationRepository extends JpaRepository<CounselingReservation, Long> {

    List<CounselingReservation> findByMember(Member member);

    List<CounselingReservation> findByCounselor(Counselor counselor);

}
