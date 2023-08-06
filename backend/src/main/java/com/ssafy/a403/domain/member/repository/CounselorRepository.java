package com.ssafy.a403.domain.member.repository;

import com.ssafy.a403.domain.member.entity.Counselor;
import com.ssafy.a403.domain.model.CounselorType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {

    // 후기 순 조회
    Page<Counselor> findAllByCounselorTypeOrderByReviewCntDesc(CounselorType counselorType, Pageable pageable);

    // 평점 순 조회
    Page<Counselor> findAllByCounselorTypeOrderByRatingAvgDesc(CounselorType counselorType, Pageable pageable);
}
