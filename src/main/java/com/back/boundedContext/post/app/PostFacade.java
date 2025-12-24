package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostMemberRepository;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.exception.DomainException;
import com.back.global.rsData.RsData;
import com.back.shared.post.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostMemberRepository postMemberRepository;
    private final PostCreateUseCase postCreateUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 게시글입니다.")
        );
    }

    @Transactional
    public RsData<Post> create(String title, String content, PostMember author) {
        return postCreateUseCase.create(title, content, author);
    }

    public PostMember syncMember(MemberDto memberDto) {
        PostMember postMember = new PostMember(
                memberDto.getId(),
                memberDto.getCreatedDate(),
                memberDto.getModifiedDate(),
                memberDto.getUsername(),
                "",
                memberDto.getNickname(),
                memberDto.getActiveScore()
        );
        return postMemberRepository.save(postMember);
    }

    @Transactional(readOnly = true)
    public PostMember findByUsername(String username) {
        return postMemberRepository.findByUsername(username).orElseThrow(
                () -> new DomainException("404-1", "존재하지 않는 username입니다.")
        );
    }
}
