package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.global.rsData.RsData;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayoutFacade {
    private final PayoutSyncMemberUseCase payoutSyncMemberUseCase;
    private final PayoutCreateUseCase payoutCreateUseCase;
    private final PayoutCompletePayoutsMoreUseCase payoutCompletePayoutsMoreUseCase;
    private final PayoutAddPayoutCandidateItemsUseCase payoutAddPayoutCandidateItemsUseCase;
    private final PayoutCollectPayoutItemsMoreUseCase payoutCollectPayoutItemsMoreUseCase;
    private final PayoutSupport payoutSupport;

    @Transactional
    public PayoutMember syncMember(MemberDto memberDto) {
        return payoutSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional
    public Payout createPayout(Long payeeId) {
        return payoutCreateUseCase.createPayout(payeeId);
    }

    @Transactional
    public void addPayoutCandidateItems(OrderDto orderDto) {
        payoutAddPayoutCandidateItemsUseCase.addPayoutCandidateItems(orderDto);
    }

    @Transactional(readOnly = true)
    public List<PayoutCandidateItem> findPayoutCandidateItems() {
        return payoutSupport.findPayoutCandidateItems();
    }

    @Transactional
    public RsData<Integer> collectPayoutItemsMore(int limit) {
        return payoutCollectPayoutItemsMoreUseCase.collectPayoutItemsMore(limit);
    }

    @Transactional
    public RsData<Integer> completePayoutsMore(int limit) {
        return payoutCompletePayoutsMoreUseCase.completePayoutsMore(limit);
    }
}
