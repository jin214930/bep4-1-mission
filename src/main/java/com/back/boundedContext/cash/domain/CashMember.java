package com.back.boundedContext.cash.domain;

import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "CASH_MEMBER")
@NoArgsConstructor
public class CashMember extends ReplicaMember {
    public CashMember(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String username, String password, String nickname, int activeScore) {
        super(id, createdDate, modifiedDate, username, password, nickname, activeScore);
    }

    public CashMemberDto toDto() {
        return new CashMemberDto(
                getId(),
                getCreatedDate(),
                getModifiedDate(),
                getUsername(),
                getNickname(),
                getActiveScore()
        );
    }
}
