package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "POST_MEMBER")
public class PostMember extends ReplicaMember {
    public PostMember(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String username, String password, String nickname, int activeScore) {
        super(id, createdDate, modifiedDate, username, password, nickname, activeScore);
    }
}
