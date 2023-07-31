package com.ssafy.a403.domain.User.repository;

import com.ssafy.a403.domain.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
