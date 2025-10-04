package com.hexagonal.domain.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @DisplayName("유효한 정보로 게시글을 생성할 수 있다.")
    @Test
    void create_ValidRequest_Success() {
        //given
        CreatePostRequest request = new CreatePostRequest("제목", "내용", 1L, PostStatus.PUBLISHED);

        //when
        Post post = Post.create(request);

        //then
        assertThat(post.getTitle()).isEqualTo(new PostTitle(request.title()));
        assertThat(post.getContent()).isEqualTo(new PostContent(request.content()));
        assertThat(post.getAuthorId()).isEqualTo(request.authorId());
        assertThat(post.getStatus()).isEqualTo(request.status());
    }
}