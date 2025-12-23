package com.back.global.initData;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.boundedContext.member.service.MemberService;
import com.back.boundedContext.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class DataInit {
    private final DataInit self;
    private final MemberService memberService;
    private final PostService postService;

    public DataInit(@Lazy DataInit self, MemberService memberService, PostService postService) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return _ -> {
            self.makeBaseMembers();
            self.makeBasePosts();
            self.makeBaseComments();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberService.count() > 0) return;

        Member systemMember = memberService.join("system", "1234", "시스템");
        Member holdingMember = memberService.join("holding", "1234", "홀딩");
        Member adminMember = memberService.join("admin", "1234", "관리자");
        Member user1Member = memberService.join("user1", "1234", "유저1");
        Member user2Member = memberService.join("user2", "1234", "유저2");
        Member user3Member = memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePosts() {
        if (postService.count() > 0) return;

        Member user1 = memberService.findByUsername("user1");
        Member user2 = memberService.findByUsername("user2");
        Member user3 = memberService.findByUsername("user3");

        Post user1Post1 = postService.create("제목1-1", "내용1-1", user1);
        Post user1Post2 = postService.create("제목1-2", "내용1-2", user1);
        Post user1Post3 = postService.create("제목1-3", "내용1-3", user1);
        Post user2Post1 = postService.create("제목2-1", "내용2-1", user2);
        Post user2Post2 = postService.create("제목2-2", "내용2-2", user2);
        Post user3Post1 = postService.create("제목3-1", "내용3-1", user3);
    }

    @Transactional
    public void makeBaseComments() {
        Post post1 = postService.findById(1);
        Post post2 = postService.findById(2);
        Post post3 = postService.findById(3);
        Post post4 = postService.findById(4);
        Post post5 = postService.findById(5);
        Post post6 = postService.findById(6);

        Member user1Member = memberService.findByUsername("user1");
        Member user2Member = memberService.findByUsername("user2");
        Member user3Member = memberService.findByUsername("user3");

        if (post1.hasComments()) return;

        post1.addComment(user1Member, "댓글1");
        post1.addComment(user2Member, "댓글2");
        post1.addComment(user3Member, "댓글3");

        post2.addComment(user2Member, "댓글4");
        post2.addComment(user2Member, "댓글5");

        post3.addComment(user3Member, "댓글6");
        post3.addComment(user3Member, "댓글7");

        post4.addComment(user1Member, "댓글8");
    }
}
