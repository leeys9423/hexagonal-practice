package com.hexagonal.domain.post;

import jakarta.persistence.Embeddable;

import static io.micrometer.common.util.StringUtils.isBlank;

@Embeddable
public record PostTitle(String title) {

    public PostTitle {
        if (title.isEmpty() || title.length() > 100) {
            throw new IllegalArgumentException("게시글 제목은 1자 이상 100자 이하여야 합니다: " + title);
        }

        if (isBlank(title)) {
            throw new IllegalArgumentException("제목은 공백으로만 구성될 수 없습니다");
        }
    }
}
