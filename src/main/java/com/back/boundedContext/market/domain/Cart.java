package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseManualIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MARKET_CART")
@NoArgsConstructor
@Getter
public class Cart extends BaseManualIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CartItem> items = new ArrayList<>();

    public Cart(MarketMember customer) {
        super(customer.getId());
        this.customer = customer;
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }

    public void addItem(Product product) {
        CartItem cartItem = new CartItem(this, product);
        items.add(cartItem);
    }
}
