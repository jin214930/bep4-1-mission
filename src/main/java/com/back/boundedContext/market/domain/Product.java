package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product extends BaseIdAndTime {
    private String sourceTypeCode;
    private long sourceId;
    private String name;
    private String description;
    private int price;
    private int salePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private MarketMember seller;
}
