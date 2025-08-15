package com.hexagonal.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
    void register_ShouldSetStatusToPending() throws Exception {
        //given
        MemberRegisterRequest request = new MemberRegisterRequest("test@test.com", "테스트닉네임", "secret");

        //when
        Member member = Member.register(request, passwordEncoder);

        //then
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
}