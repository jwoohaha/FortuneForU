package com.ssafy.a403.domain.member.service;

import com.ssafy.a403.domain.member.entity.Member;
import com.ssafy.a403.domain.member.repository.MemberRepository;
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
public class MemberService {
    private final MemberRepository userRepository;

    public Member findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Boolean checkLoginIdDuplication( ) {

        return null;
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Member> member = userRepository.findById(id);
        if (member.isEmpty()) {
            return;
        }
        userRepository.delete(member.get());
    }

    public Page<Member> findPaging(Pageable pageable) {
        return userRepository.findPaging(pageable);
    }
}
