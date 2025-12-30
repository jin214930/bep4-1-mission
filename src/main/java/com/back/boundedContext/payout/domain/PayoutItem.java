package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYOUT_PAYOUT_ITEM")
@Getter
@NoArgsConstructor
public class PayoutItem extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Payout payout;

    @Enumerated(EnumType.STRING)
    private PayoutEventType eventType;

    private String relTypeCode;

    private Long relId;

    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payer;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    private long amount;

    public PayoutItem(Payout payout, PayoutEventType eventType, String relTypeCode, long relId, LocalDateTime payDate, PayoutMember payer, PayoutMember payee, long amount) {
        this.payout = payout;
        this.eventType = eventType;
        this.relTypeCode = relTypeCode;
        this.relId = relId;
        this.paymentDate = payDate;
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }
}
