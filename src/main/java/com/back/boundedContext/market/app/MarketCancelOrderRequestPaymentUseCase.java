package com.back.boundedContext.market.app;

import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.out.OrderRepository;
import com.back.global.exception.DomainException;
import com.back.shared.cash.event.CashOrderPaymentFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCancelOrderRequestPaymentUseCase {
    private final OrderRepository orderRepository;

    public void handle(CashOrderPaymentFailedEvent event) {
        Order order = orderRepository.findById(event.getOrderDto().getId()).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 주문입니다.")
        );

        order.cancelRequestPayment();
    }
}
