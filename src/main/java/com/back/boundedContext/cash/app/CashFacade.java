package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashSyncMemberUseCase cashSyncMemberUseCase;
    private final CashCompleteOrderPaymentUseCase cashCompleteOrderPaymentUseCase;
    private final CashCompletePayoutUseCase cashCompletePayoutUseCase;
    private final CashSupport cashSupport;

    @Transactional
    public CashMember syncMember(MemberDto memberDto) {
        return cashSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional
    public Wallet createWallet(CashMemberDto memberDto) {
        return cashCreateWalletUseCase.createWallet(memberDto);
    }

    @Transactional(readOnly = true)
    public CashMember findMemberByUsername(String username) {
        return cashSupport.findMemberByUsername(username);
    }

    @Transactional(readOnly = true)
    public Wallet findWalletByHolder(CashMember holder) {
        return cashSupport.findWalletByHolder(holder);
    }

    @Transactional
    public void completeOrderPayment(OrderDto orderDto, long pgPaymentAmount) {
        cashCompleteOrderPaymentUseCase.completeOrderPayment(orderDto, pgPaymentAmount);
    }

    @Transactional(readOnly = true)
    public Wallet findWalletByHolderId(long holderId) {
        return cashSupport.findWalletByHolderId(holderId);
    }


    @Transactional
    public void completePayout(PayoutDto payoutDto) {
        cashCompletePayoutUseCase.completePayout(payoutDto);
    }
}
