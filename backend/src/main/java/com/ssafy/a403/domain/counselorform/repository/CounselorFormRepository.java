package com.ssafy.a403.domain.member.repository;

import com.ssafy.a403.domain.member.entity.CounselorForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CounselorFormRepository extends JpaRepository<CounselorForm, Long> {

    @Query(value = "select f from CounselorForm f order by f.created desc",
            countQuery = "select count(f) from CounselorForm f")
    Page<CounselorForm> findAllPaging(Pageable pageable);
}
