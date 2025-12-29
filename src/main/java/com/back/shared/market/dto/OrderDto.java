package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderDto {
    private final Long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final Long customerId;
    private final String customerName;
    private final long price;
    private final long salePrice;
    private final LocalDateTime requestPaymentDate;
    private final LocalDateTime paymentDate;

    public OrderDto(Order order) {
        this(
                order.getId(),
                order.getCreatedDate(),
                order.getModifiedDate(),
                order.getCustomer().getId(),
                order.getCustomer().getNickname(),
                order.getPrice(),
                order.getSalePrice(),
                order.getRequestPaymentDate(),
                order.getPaymentDate()
        );
    }
}
