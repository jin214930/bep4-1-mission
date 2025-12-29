package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.global.event.EventPublisher;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final EventPublisher eventPublisher;

    public CashMember syncMember(MemberDto memberDto) {
        boolean isNew = !cashMemberRepository.existsById(memberDto.getId());

        CashMember member = new CashMember(
                memberDto.getId(),
                memberDto.getCreatedDate(),
                memberDto.getModifiedDate(),
                memberDto.getUsername(),
                "",
                memberDto.getNickname(),
                memberDto.getActiveScore()
        );

        member = cashMemberRepository.save(member);

        if (isNew) {
            eventPublisher.publish(new CashMemberCreatedEvent(member.toDto()));
        }

        return member;
    }
}
