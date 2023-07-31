package com.ssafy.a403.domain.user.repository;

import com.ssafy.a403.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
