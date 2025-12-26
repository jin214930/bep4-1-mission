package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARKET_ORDER")
@NoArgsConstructor
@Getter
public class Order extends BaseIdAndTime {
    private LocalDateTime requestPaymentDate;
    private LocalDateTime paymentDate;
    private LocalDateTime cancelDate;
    private LocalDateTime refundDate;
    private int price;
    private int salePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItem> items = new ArrayList<>();

    public Order(Cart cart) {
        customer = cart.getCustomer();

        cart.getItems().forEach(item -> {
            addItem(item.getProduct());
        });
    }

    public void addItem(Product product) {
        OrderItem orderItem = new OrderItem(
                product.getName(),
                product.getPrice(),
                product.getSalePrice(),
                this,
                product
        );

        items.add(orderItem);

        price += product.getPrice();
        salePrice += product.getSalePrice();
    }

    public void completePayment() {
        paymentDate = LocalDateTime.now();
    }

    public boolean isPaid() {
        return paymentDate != null;
    }
}
