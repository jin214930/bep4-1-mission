package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class PostMember extends BaseIdAndTime {
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
    private int activeScore;
}
