package com.hexagonal.domain.member;

public interface PasswordEncoder {
    String encode(CharSequence password);
    boolean matches(CharSequence password, String passwordHash);
}
