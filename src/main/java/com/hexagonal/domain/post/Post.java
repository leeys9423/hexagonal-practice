package com.hexagonal.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PostTitle title;

    @Embedded
    private PostContent content;

    private Long authorId;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Post create(CreatePostRequest request) {
        Post post = new Post();

        String title = requireNonNull(request.title());
        post.title = new PostTitle(title);

        String content = requireNonNull(request.content());
        post.content = new PostContent(content);

        post.authorId = requireNonNull(request.authorId());

        post.status = requireNonNull(request.status());

        post.createdAt = LocalDateTime.now();

        post.updatedAt = LocalDateTime.now();

        return post;
    }
}
