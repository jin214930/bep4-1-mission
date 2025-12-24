package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.PostComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostCommentDto {
    private final Long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final String content;
    private final long postId;
    private final long authorId;
    private final String authorName;

    public PostCommentDto(PostComment postComment) {
        this(
                postComment.getId(),
                postComment.getCreatedDate(),
                postComment.getModifiedDate(),
                postComment.getContent(),
                postComment.getPost().getId(),
                postComment.getAuthor().getId(),
                postComment.getAuthor().getNickname()
        );
    }
}
