package me.devksh930.orderapi.account.domain;

import org.springframework.util.StringUtils;

public enum AccountGender {
    MALE, FEMALE, NONE;

    public static AccountGender of(final String role) {
        if (StringUtils.hasText(role)) {
            return AccountGender.valueOf(role.toUpperCase());
        }
        return AccountGender.NONE;
    }
}