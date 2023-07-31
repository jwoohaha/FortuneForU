package com.ssafy.a403.domain.User.repository;

import com.ssafy.a403.domain.User.entity.Follow;
import com.ssafy.a403.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Follow.PK> {
    Optional<Follow> findByToUserAndFromUser(Long toUserId, Long fromUserId);
    List<Follow> findAll();

    @Query(value = "select u from Follow f INNER JOIN User u ON f.fromUser = u.userNo where f.toUser = :userId")
    List<User> findAllByToUser(@Param("userId") Long userId);

    void deleteAllByFromUser(Long userId);
    void deleteAllByToUser(Long userId);
}
