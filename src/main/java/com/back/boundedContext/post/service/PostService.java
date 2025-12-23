package com.back.boundedContext.post.service;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.boundedContext.global.exception.DomainException;
import com.back.boundedContext.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public long count() {
        return postRepository.count();
    }

    public Post create(String title, String content, Member author) {
        Post post = new Post(title, content, author);
        author.increaseScore(3);
        return postRepository.save(post);
    }

    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 게시글입니다.")
        );
    }
}
