package com.hexagonal.domain.member;

public class FakePasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String password) {
        return "encoded_" + password;
    }

    @Override
    public boolean matches(String password, String passwordHash) {
        return passwordHash.equals("encoded_" + password);
    }
}
