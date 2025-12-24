package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.global.rsData.RsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class PostDataInit {
    private final PostDataInit self;
    private final PostFacade postFacade;

    public PostDataInit(@Lazy PostDataInit self, PostFacade postFacade) {
        this.self = self;
        this.postFacade = postFacade;
    }

    @Bean
    @Order(2)
    public ApplicationRunner postInitDataRunner() {
        return _ -> {
            self.makeBasePosts();
            self.makeBaseComments();
        };
    }

    @Transactional
    public void makeBasePosts() {
        if (postFacade.count() > 0) return;

        PostMember user1 = postFacade.findByUsername("user1");
        PostMember user2 = postFacade.findByUsername("user2");
        PostMember user3 = postFacade.findByUsername("user3");

        RsData<Post> post1RsData = postFacade.create("제목1", "내용1", user1);
        log.debug(post1RsData.getMsg());

        RsData<Post> post2RsData = postFacade.create("제목2", "내용2", user1);
        log.debug(post2RsData.getMsg());

        RsData<Post> post3RsData = postFacade.create("제목3", "내용3", user1);
        log.debug(post3RsData.getMsg());

        RsData<Post> post4RsData = postFacade.create("제목4", "내용4", user2);
        log.debug(post4RsData.getMsg());

        RsData<Post> post5RsData = postFacade.create("제목5", "내용5", user2);
        log.debug(post5RsData.getMsg());

        RsData<Post> post6RsData = postFacade.create("제목6", "내용6", user3);
        log.debug(post6RsData.getMsg());
    }

    @Transactional
    public void makeBaseComments() {
        Post post1 = postFacade.findById(1);
        Post post2 = postFacade.findById(2);
        Post post3 = postFacade.findById(3);
        Post post4 = postFacade.findById(4);
        Post post5 = postFacade.findById(5);
        Post post6 = postFacade.findById(6);

        PostMember user1Member = postFacade.findByUsername("user1");
        PostMember user2Member = postFacade.findByUsername("user2");
        PostMember user3Member = postFacade.findByUsername("user3");

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
