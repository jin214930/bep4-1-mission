package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PAYOUT_PAYOUT")
@Getter
@NoArgsConstructor
public class Payout extends BaseIdAndTime {
    @Setter
    private LocalDateTime payoutDate;

    private long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    @OneToMany(mappedBy = "payout", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PayoutItem> items = new ArrayList<>();

    public Payout(PayoutMember payee) {
        this.payee = payee;
    }

    public PayoutItem addItem(PayoutEventType eventType, String relTypeCode, long relId, LocalDateTime payDate, PayoutMember payer, PayoutMember payee, long amount) {
        PayoutItem item = new PayoutItem(this, eventType, relTypeCode, relId, payDate, payer, payee, amount);
        items.add(item);
        this.amount += amount;
        return item;
    }
}
