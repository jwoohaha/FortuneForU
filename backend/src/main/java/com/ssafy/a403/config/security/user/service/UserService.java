package com.ssafy.a403.config.security.user.service;

import com.ssafy.a403.config.security.user.dao.UserRepository;
import com.ssafy.a403.config.security.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Boolean checkLoginIdDuplication( ) {

        return null;
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<User> member = userRepository.findById(id);
        if (member.isEmpty()) {
            return;
        }
        userRepository.delete(member.get());
    }

    public Page<User> findPaging(Pageable pageable) {
        return userRepository.findPaging(pageable);
    }
}
