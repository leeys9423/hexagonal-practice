package com.hexagonal.domain.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostTitleTest {

    @Test
    void equality() {
        var title1 = new PostTitle("제목");
        var title2 = new PostTitle("제목");

        assertThat(title1).isEqualTo(title2);
    }

    @DisplayName("제목은 1글자 이상 100글자 이하여야 한다.")
    @Test
    void postTitle_ShouldBeBetween1And100Characters() {
        //given
        String validTitle = "제목입니다!";

        //when
        PostTitle postTitle = new PostTitle(validTitle);

        //then
        assertThat(postTitle.title()).isEqualTo(validTitle);
    }

    @DisplayName("제목은 공백으로 이루어질 수 없다.")
    @Test
    void postTitle_ShouldNotBeOnlyBlank() {
        //given
        String invalidTitle = " ";

        //when & then
        assertThatThrownBy(() -> new PostTitle(invalidTitle))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("제목은 100글자 초과일 수 없다.")
    @Test
    void postTitle_ShouldNotBeGreaterThan100Characters() {
        //given
        String invalidTitle = "VeryLooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooonTitle";

        //when & then
        assertThatThrownBy(() -> new PostTitle(invalidTitle))
                .isInstanceOf(IllegalArgumentException.class);
    }
}