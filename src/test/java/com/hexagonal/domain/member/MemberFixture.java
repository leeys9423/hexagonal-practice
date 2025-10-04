package com.hexagonal.domain.member;

import java.lang.reflect.Field;

public class MemberFixture {

    private static final PasswordEncoder PASSWORD_ENCODER = new FakePasswordEncoder();

    public static Member createMember() throws Exception {
        Member member = Member.register(new MemberRegisterRequest("test@test.com", "yslee", "password"), PASSWORD_ENCODER);

        Field idField = Member.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(member, 1L);

        return member;
    }
}
