package com.back.boundedContext.post.entity;

import com.back.boundedContext.global.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comment extends BaseIdAndTime {
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    public Comment(String content, Post post, Member author) {
        this.content = content;
        this.post = post;
        this.author = author;
    }
}
