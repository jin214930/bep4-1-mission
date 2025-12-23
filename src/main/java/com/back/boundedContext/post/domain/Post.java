package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.domain.Member;
import com.back.shared.post.dto.CommentDto;
import com.back.shared.post.event.CommentCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseIdAndTime {
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, Member author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public boolean hasComments() {
        return !comments.isEmpty();
    }

    public void addComment(Member author, String content) {
        Comment comment = new Comment(content, this, author);
        comments.add(comment);
        publishEvent(new CommentCreatedEvent(new CommentDto(comment)));
    }
}
