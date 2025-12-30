package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import com.back.boundedContext.payout.domain.PayoutEventType;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutCandidateItemRepository;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.market.dto.OrderItemDto;
import com.back.shared.market.out.MarketApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayoutAddPayoutCandidateItemsUseCase {
    private final MarketApiClient marketApiClient;
    private final PayoutSupport payoutSupport;
    private final PayoutCandidateItemRepository payoutCandidateItemRepository;

    public void addPayoutCandidateItems(OrderDto orderDto) {
        marketApiClient.getOrderItems(orderDto.getId())
                .forEach(item -> makePayoutCandidateItems(orderDto, item));

    }

    private void makePayoutCandidateItems(OrderDto orderDto, OrderItemDto orderItemDto) {
        PayoutMember holding = payoutSupport.findHoldingMember();
        PayoutMember buyer = payoutSupport.findMemberById(orderDto.getCustomerId());
        PayoutMember seller = payoutSupport.findMemberById(orderItemDto.getSellerId());

        makePayoutCandidateItem(
                PayoutEventType.정산__상품판매_수수료,
                orderItemDto.getModelTypeCode(),
                orderItemDto.getId(),
                orderDto.getPaymentDate(),
                buyer,
                holding,
                orderItemDto.getPayoutFee()
        );

        makePayoutCandidateItem(
                PayoutEventType.정산__상품판매_대금,
                orderItemDto.getModelTypeCode(),
                orderItemDto.getId(),
                orderDto.getPaymentDate(),
                buyer,
                seller,
                orderItemDto.getSalePriceWithoutFee()
        );
    }

    private void makePayoutCandidateItem(PayoutEventType eventType, String relTypeCode, Long relId,
                                         LocalDateTime paymentDate, PayoutMember payer, PayoutMember payee, long amount
    ) {
        PayoutCandidateItem item = new PayoutCandidateItem(
                eventType,
                relTypeCode,
                relId,
                payer,
                payee,
                amount
        );

        payoutCandidateItemRepository.save(item);
    }
}
