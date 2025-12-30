package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.payout.dto.PayoutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCompletePayoutUseCase {
    private final CashSupport cashSupport;

    public void completePayout(PayoutDto payoutDto) {
        Wallet holdingWallet = cashSupport.findHoldingWallet();
        Wallet payeeWallet = cashSupport.findWalletByHolderId(payoutDto.getPayeeId());

        holdingWallet.debit(
                payoutDto.getAmount(),
                payoutDto.isPayeeSystem() ? CashLog.EventType.정산지급__상품판매_수수료 : CashLog.EventType.정산지급__상품판매_대금,
                payoutDto.getModelTypeCode(),
                payoutDto.getId()
        );

        payeeWallet.credit(
                payoutDto.getAmount(),
                payoutDto.isPayeeSystem() ? CashLog.EventType.정산수령__상품판매_수수료 : CashLog.EventType.정산수령__상품판매_대금,
                payoutDto.getModelTypeCode(),
                payoutDto.getId()
        );
    }
}
