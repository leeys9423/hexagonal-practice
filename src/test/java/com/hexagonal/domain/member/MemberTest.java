package com.hexagonal.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(String password) {
            System.out.println(password + " encoding test");
            return "";
        }

        @Override
        public boolean matches(String password, String passwordHash) {
            return false;
        }
    };

    @DisplayName("회원 등록 시 상태는 PENDING이다.")
    @Test
    void register_ShouldSetStatusToPending() {
        //given
        MemberRegisterRequest request = new MemberRegisterRequest("test@test.com", "테스트닉네임", "secret");

        //when
        Member member = Member.register(request, passwordEncoder);

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
    
    @DisplayName("회원 활성화는 PENDING 상태에서만 가능하다.")
    @Test
    void activate_ShouldSetStatusToActive_WhenStatusIsPending() {
        //given
        MemberRegisterRequest request = new MemberRegisterRequest("test@test.com", "테스트닉네임", "secret");
        Member member = Member.register(request, passwordEncoder);

        //when
        member.activate();

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }
    
    @DisplayName("PENDING이 아닌 상태에서는 활성화할 수 없다.")
    @Test
    void activate_ShouldThrowException_WhenStatusIsNotPending() {
        //given
        MemberRegisterRequest request = new MemberRegisterRequest("test@test.com", "테스트닉네임", "secret");
        Member member = Member.register(request, passwordEncoder);
        member.activate();
        member.deactivate();
        
        //when & then
        assertThatThrownBy(() -> member.activate()).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("ACTIVE가 아닌 상태에서는 비활성화할 수 없다.")
    @Test
    void deactivate_ShouldThrowException_WhenStatusIsNotActive() {
        //given
        MemberRegisterRequest request = new MemberRegisterRequest("test@test.com", "테스트닉네임", "secret");
        Member member = Member.register(request, passwordEncoder);

        //when & then
        assertThatThrownBy(() -> member.deactivate()).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("ACTIVE 상태에서만 비활성화할 수 있다.")
    @Test
    void deactivate_ShouldSetStatusToDeactivated_WhenStatusIsActive() {
        //given
        MemberRegisterRequest request = new MemberRegisterRequest("test@test.com", "테스트닉네임", "secret");
        Member member = Member.register(request, passwordEncoder);
        member.activate();

        //when
        member.deactivate();

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }
}