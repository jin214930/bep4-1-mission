package com.back.shared.post.dto;

import com.back.boundedContext.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostDto {
    private final long id;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final String title;
    private final String content;
    private final long authorId;
    private final String authorName;

    public PostDto(Post post) {
        this(
                post.getId(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname()
        );
    }
}
