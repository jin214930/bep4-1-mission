package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostCreateUseCase postCreateUseCase;
    private final PostSyncMemberUseCase postSyncMemberUseCase;
    private final PostSupport postSupport;

    @Transactional(readOnly = true)
    public long count() {
        return postSupport.count();
    }

    @Transactional(readOnly = true)
    public Post findById(long id) {
        return postSupport.findById(id);
    }

    @Transactional(readOnly = true)
    public PostMember findByUsername(String username) {
        return postSupport.findByUsername(username);
    }

    @Transactional
    public RsData<Post> create(String title, String content, PostMember author) {
        return postCreateUseCase.create(title, content, author);
    }

    @Transactional
    public void syncMember(MemberDto memberDto) {
        postSyncMemberUseCase.syncMember(memberDto);
    }

    @Transactional(readOnly = true)
    public List<Post> findByOrderByIdDesc() {
        return postSupport.findByOrderByIdDesc();
    }
}
