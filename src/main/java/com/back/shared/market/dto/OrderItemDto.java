package com.back.shared.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class OrderItemDto {
    private final Long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final Long orderId;
    private final Long buyerId;
    private final String buyerName;
    private final Long sellerId;
    private final String sellerName;
    private final Long productId;
    private final String productName;
    private final int price;
    private final int salePrice;
    private final double payoutRate;
}
