package com.back.shared.post.event;

import com.back.shared.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberModifiedEvent {
    private final MemberDto memberDto;
}
