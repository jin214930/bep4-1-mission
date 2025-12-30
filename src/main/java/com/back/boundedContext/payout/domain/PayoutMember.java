package com.back.boundedContext.payout.domain;

import com.back.shared.member.domain.ReplicaMember;
import com.back.shared.payout.dto.PayoutMemberDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PayoutMember extends ReplicaMember {
    public PayoutMember(Long id, java.time.LocalDateTime createdDate, java.time.LocalDateTime modifiedDate, String username, String password, String nickname, int activeScore) {
        super(id, createdDate, modifiedDate, username, password, nickname, activeScore);
    }

    public PayoutMemberDto toDto() {
        return new PayoutMemberDto(
                getId(),
                getCreatedDate(),
                getModifiedDate(),
                getUsername(),
                getNickname(),
                getActiveScore()
        );
    }

    public boolean isSystem() {
        return getUsername().equals("system");
    }
}
