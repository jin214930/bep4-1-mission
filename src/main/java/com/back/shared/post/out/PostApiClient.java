package com.back.shared.post.out;

import com.back.shared.post.dto.PostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostApiClient {
    private final RestClient restClient;

    public PostApiClient(@Value("${custom.global.internalBackUrl}") String internalBackUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(internalBackUrl + "/post/api/v1")
                .build();
    }

    public List<PostDto> getPosts() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public PostDto getPost(long id) {
        return restClient.get()
                .uri("/posts/%d".formatted(id))
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
