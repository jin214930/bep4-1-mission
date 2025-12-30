package com.back.boundedContext.payout.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAYOUT_PAYOUT_CANDIDATE_ITEM")
@Getter
@NoArgsConstructor
public class PayoutCandidateItem extends BaseIdAndTime {
    @Enumerated(EnumType.STRING)
    private PayoutEventType eventType;

    String relTypeCode;

    private Long relId;

    private LocalDateTime paymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payer;

    @ManyToOne(fetch = FetchType.LAZY)
    private PayoutMember payee;

    private long amount;

    @OneToOne(fetch = FetchType.LAZY)
    @Setter
    private PayoutItem payoutItem;

    public PayoutCandidateItem(PayoutEventType eventType, String relTypeCode, Long relId, PayoutMember payer, PayoutMember payee, long amount) {
        this.eventType = eventType;
        this.relTypeCode = relTypeCode;
        this.relId = relId;
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

}
