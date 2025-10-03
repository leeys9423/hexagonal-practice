package com.hexagonal.domain.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @Size(min = 1, max = 100) String title,
        @Size(min = 1, max = 10_000) String content,
        @NotNull Long authorId,
        @NotNull PostStatus status) {
}
