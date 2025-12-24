package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.event.EventPublisher;
import com.back.shared.post.dto.PostDto;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCreateUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public Post create(String title, String content, Member author) {
        Post post = postRepository.save(new Post(title, content, author));
        
        eventPublisher.publish(new PostCreatedEvent(new PostDto(post)));

        return post;
    }
}
