package com.back.shared.post.dto;

import com.back.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MemberDto {
    private final Long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final String username;
    private final String nickname;
    private final int activeScore;

    public MemberDto(Member member) {
        this(
                member.getId(),
                member.getCreatedDate(),
                member.getModifiedDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActiveScore()
        );
    }
}
