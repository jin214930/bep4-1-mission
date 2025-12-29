package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.shared.post.dto.PostCommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "POST_POST_COMMENT")
public class PostComment extends BaseIdAndTime {
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostMember author;

    public PostComment(String content, Post post, PostMember author) {
        this.content = content;
        this.post = post;
        this.author = author;
    }

    public PostCommentDto toDto() {
        return new PostCommentDto(
                getId(),
                getCreatedDate(),
                getModifiedDate(),
                content,
                post.getId(),
                author.getId(),
                author.getNickname()
        );
    }
}
