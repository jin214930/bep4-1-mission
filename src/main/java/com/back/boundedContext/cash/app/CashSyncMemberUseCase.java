package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;

    public CashMember syncMember(MemberDto memberDto) {
        CashMember member = new CashMember(
                memberDto.getId(),
                memberDto.getCreatedDate(),
                memberDto.getModifiedDate(),
                memberDto.getUsername(),
                "",
                memberDto.getNickname(),
                memberDto.getActiveScore()
        );

        return cashMemberRepository.save(member);
    }
}
