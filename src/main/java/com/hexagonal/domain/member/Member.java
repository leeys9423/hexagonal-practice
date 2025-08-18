package com.hexagonal.domain.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;
import static org.springframework.util.Assert.state;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @NaturalId
    private Email email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime lastLoginAt;

    public static Member register(MemberRegisterRequest request, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = new Email(request.email());
        member.nickname = requireNonNull(request.nickname());
        member.passwordHash = passwordEncoder.encode(requireNonNull(request.password()));

        member.status = MemberStatus.PENDING;

        return member;
    }

    public void activate() {
        state(this.status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        state(this.status == MemberStatus.ACTIVE, "ACTIVE 상태가 아닙니다");

        this.status = MemberStatus.DEACTIVATED;
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
