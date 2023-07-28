package com.ssafy.a403.domain.repository;

import com.ssafy.a403.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
