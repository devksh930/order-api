package me.devksh930.orderapi.account.dto;

import lombok.Getter;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.AccountGender;

@Getter
public class AccountResponse {
    private final Long id;
    private final String name;
    private final String nickname;
    private final String phoneNumber;
    private final String email;
    private final AccountGender accountGender;

    private AccountResponse(final Long id, final String name, final String nickname, final String phoneNumber, final String email, final AccountGender accountGender) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountGender = accountGender;
    }

    public static AccountResponse of(final Account account) {
        return new AccountResponse(account.getId(), account.getName(), account.getNickname(), account.getPhoneNumber(), account.getEmail(), account.getAccountGender());
    }
}
