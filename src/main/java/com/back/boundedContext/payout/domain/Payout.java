package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.payout.dto.PayoutDto;
import com.back.shared.payout.event.PayoutCompletedEvent;
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

    public void completePayout() {
        payoutDate = LocalDateTime.now();

        publishEvent(
                new PayoutCompletedEvent(toDto())
        );
    }

    public PayoutDto toDto() {
        return new PayoutDto(
                getId(),
                getCreatedDate(),
                getModifiedDate(),
                payee.getId(),
                payee.getNickname(),
                payoutDate,
                amount,
                payee.isSystem()
        );
    }
}
