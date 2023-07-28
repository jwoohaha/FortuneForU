package com.ssafy.a403.domain.repository;

import com.ssafy.a403.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
