package com.ssafy.a403.domain.member.repository;

import com.ssafy.a403.domain.member.entity.Counselor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    @Query(value = "select m from Counselor m order by m.no desc ",
            countQuery = "select count(m) from Counselor m")
    Page<Counselor> findPaging(Pageable pageable);
}
