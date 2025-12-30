package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.market.dto.OrderItemDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_ORDER_ITEM")
@NoArgsConstructor
@Getter
public class OrderItem extends BaseIdAndTime {
    private String productName;
    private int price;
    private int salePrice;
    private double payoutRate = MarketPolicy.PRODUCT_PAYOUT_RATE;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public OrderItem(String productName, int price, int salePrice, Order order, Product product) {
        this.productName = productName;
        this.price = price;
        this.salePrice = salePrice;
        this.order = order;
        this.product = product;
    }

    public OrderItemDto toDto() {
        return new OrderItemDto(
                getId(),
                getCreatedDate(),
                getModifiedDate(),
                order.getId(),
                order.getCustomer().getId(),
                order.getCustomer().getNickname(),
                product.getSeller().getId(),
                product.getSeller().getNickname(),
                product.getId(),
                productName,
                price,
                salePrice,
                payoutRate,
                getPayoutFee(),
                getSalePriceWithoutFee()
        );
    }

    public long getPayoutFee() {
        return MarketPolicy.calculatePayoutFee(getSalePrice(), getPayoutRate());
    }

    public long getSalePriceWithoutFee() {
        return MarketPolicy.calculateSalePriceWithoutFee(getSalePrice(), getPayoutRate());
    }
}
