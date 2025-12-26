package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberSupport {
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 username입니다.")
        );
    }

    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 회원입니다.")
        );
    }
}
