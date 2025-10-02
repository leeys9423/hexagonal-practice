package com.hexagonal.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PostTitle title;

    private String content;

    private Long authorId;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
