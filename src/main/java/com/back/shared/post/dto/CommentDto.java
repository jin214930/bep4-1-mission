package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentDto {
    private final Long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final String content;
    private final long postId;
    private final long authorId;
    private final String authorName;

    public CommentDto(Comment comment) {
        this(
                comment.getId(),
                comment.getCreatedDate(),
                comment.getModifiedDate(),
                comment.getContent(),
                comment.getPost().getId(),
                comment.getAuthor().getId(),
                comment.getAuthor().getNickname()
        );
    }
}
