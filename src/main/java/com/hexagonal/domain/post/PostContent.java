package com.hexagonal.domain.post;

import jakarta.persistence.Embeddable;

@Embeddable
public record PostContent(String content) {

    public PostContent {
        if (content.isEmpty() || content.length() > 10_000) {
            throw new IllegalArgumentException("게시글 내용은 1자 이상 10,000자 이하여야 합니다");
        }
    }
}
