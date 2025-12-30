package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import com.back.global.event.EventPublisher;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSyncMemberUseCase {
    private final PayoutMemberRepository payoutMemberRepository;
    private final EventPublisher eventPublisher;

    public PayoutMember syncMember(MemberDto memberDto) {
        boolean isNew = !payoutMemberRepository.existsById(memberDto.getId());

        PayoutMember member = payoutMemberRepository.save(
                new PayoutMember(
                        memberDto.getId(),
                        memberDto.getCreatedDate(),
                        memberDto.getModifiedDate(),
                        memberDto.getUsername(),
                        "",
                        memberDto.getNickname(),
                        memberDto.getActiveScore()
                )
        );

        if (isNew) {
            eventPublisher.publish(
                    new PayoutMemberCreatedEvent(
                            member.toDto()
                    )
            );
        }

        return member;
    }
}
