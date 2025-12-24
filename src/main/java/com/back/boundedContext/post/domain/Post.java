package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.domain.Member;
import com.back.shared.post.dto.PostCommentDto;
import com.back.shared.post.event.CommentCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "POST_POST")
public class Post extends BaseIdAndTime {
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostMember author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PostComment> postComments = new ArrayList<>();

    public Post(String title, String content, PostMember author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public boolean hasComments() {
        return !postComments.isEmpty();
    }

    public void addComment(Member author, String content) {
        PostComment postComment = new PostComment(content, this, author);
        postComments.add(postComment);
        publishEvent(new CommentCreatedEvent(new PostCommentDto(postComment)));
    }
}
