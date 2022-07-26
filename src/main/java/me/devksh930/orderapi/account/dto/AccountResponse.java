package me.devksh930.orderapi.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.AccountGender;

@Getter
public class AccountResponse {
    @Schema(description = "db상의 id",required = true)
    private final Long id;
    @Schema(description = "이름",required = true)
    private final String name;
    @Schema(description = "닉네임",required = true)
    private final String nickname;
    @Schema(description = "전화번호",required = true)
    private final String phoneNumber;
    @Schema(description = "이메일",required = true)
    private final String email;
    @Schema(description = "성별",required = false)
    private final AccountGender accountGender;

    public AccountResponse(final Long id, final String name, final String nickname, final String phoneNumber, final String email, final AccountGender accountGender) {
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
