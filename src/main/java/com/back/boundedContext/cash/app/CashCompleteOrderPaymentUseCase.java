package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashLog;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.global.event.EventPublisher;
import com.back.shared.cash.event.CashOrderPaymentFailedEvent;
import com.back.shared.cash.event.CashOrderPaymentSucceededEvent;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashCompleteOrderPaymentUseCase {
    private final CashSupport cashSupport;
    private final EventPublisher eventPublisher;

    public void handle(MarketOrderPaymentRequestedEvent event) {
        Wallet customerWallet = cashSupport.findWalletByHolderId(event.getOrderDto().getCustomerId());
        Wallet holderWallet = cashSupport.findHoldingWallet();

        if (event.getPgPaymentAmount() > 0) {
            customerWallet.credit(
                    event.getPgPaymentAmount(),
                    CashLog.EventType.충전__PG결제_토스페이먼츠,
                    "Order",
                    event.getOrderDto().getId()
            );
        }

        boolean canPay = customerWallet.getBalance() >= event.getOrderDto().getSalePrice();

        if (canPay) {
            customerWallet.debit(
                    event.getOrderDto().getSalePrice(),
                    CashLog.EventType.사용__주문결제,
                    "Order",
                    event.getOrderDto().getId()
            );

            holderWallet.credit(
                    event.getOrderDto().getSalePrice(),
                    CashLog.EventType.임시보관__주문결제,
                    "Order",
                    event.getOrderDto().getId()
            );

            eventPublisher.publish(
                    new CashOrderPaymentSucceededEvent(
                            event.getOrderDto(),
                            event.getPgPaymentAmount()
                    )
            );
        } else {
            eventPublisher.publish(
                    new CashOrderPaymentFailedEvent(
                            "400-1",
                            "충전은 완료했지만 %d번 주문을 결제완료처리를 하기에는 예치금이 부족합니다.".formatted(event.getOrderDto().getId()),
                            event.getOrderDto(),
                            event.getPgPaymentAmount(),
                            event.getPgPaymentAmount() - customerWallet.getBalance()
                    )
            );
        }
    }
}
