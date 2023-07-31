package com.ssafy.a403.global.config.security.user.dao;

import com.ssafy.a403.global.config.security.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauth2AccountId(String accountId);

//    Optional<User> findByid(String loginId);
//
//    Boolean existsByLoginId(String loginId);

    @Query(value = "select m from User m order by m.id desc ",
            countQuery = "select count(m) from User m")
    Page<User> findPaging(Pageable pageable);
}
