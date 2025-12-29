package com.back.boundedContext.member.domain;

import com.back.shared.member.domain.SourceMember;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.post.event.MemberModifiedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "MEMBER_MEMBER")
public class Member extends SourceMember {
    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    public MemberDto toDto() {
        return new MemberDto(
                getId(),
                getCreatedDate(),
                getModifiedDate(),
                getUsername(),
                getNickname(),
                getActiveScore()
        );
    }

    public void increaseScore(int score) {
        if (score == 0)
            return;

        setActiveScore(getActiveScore() + score);

        publishEvent(new MemberModifiedEvent(toDto()));
    }
}
