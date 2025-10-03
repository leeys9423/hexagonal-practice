package com.hexagonal.domain.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostContentTest {

    @Test
    void equality() {
        PostContent content1 = new PostContent("내용입니다.");
        PostContent content2 = new PostContent("내용입니다.");

        assertThat(content1).isEqualTo(content2);
    }

    @DisplayName("게시글의 내용은 1글자 이상 10,000글자 이하여야 한다.")
    @Test
    void postContent_ShouldBeBetween1And100000Characters() {
        //given
        String content = "유효한 게시글 내용입니다.";

        //when
        PostContent postContent = new PostContent(content);

        //then
        assertThat(postContent.content()).isEqualTo(content);
    }

    @DisplayName("게시글 내용은 0글자로 이뤄질 수 없다.")
    @Test
    void postContent_ShouldNotBe0Character() {
        //given
        String content = "";
        //when & then
        assertThatThrownBy(() -> new PostContent(content))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("게시글 내용은 10,000글자가 초과일 수 없다.")
    @Test
    void postContent_ShouldNotGreaterThan100000Characters() {
        //given
        String content = "a".repeat(10_001);

        //when & then
        assertThatThrownBy(() -> new PostContent(content))
                .isInstanceOf(IllegalArgumentException.class);
    }
}