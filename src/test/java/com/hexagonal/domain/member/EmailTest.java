package com.hexagonal.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {

    @Test
    void equality() {
        var email1 = new Email("test@test.com");
        var email2 = new Email("test@test.com");

        assertThat(email1).isEqualTo(email2);
    }

    @DisplayName("이메일 형식에 맞게 이메일을 객체를 생성해야 한다.")
    @Test
    void email_ShouldBeSetCorrectPattern() {
        //given
        String validEmail = "test@example.com";

        //when
        Email email = new Email(validEmail);

        //then
        assertThat(email.address()).isEqualTo(validEmail);
    }

    @DisplayName("잘못된 이메일 형식으로는 Email 객체를 생성할 수 없다.")
    @Test
    void email_ShouldThrowException_WhenInvalidFormat() {
        //given
        String invalidEmail = "invalid-email";

        //when & then
        assertThatThrownBy(() -> new Email(invalidEmail))
                .isInstanceOf(IllegalArgumentException.class);
    }
}