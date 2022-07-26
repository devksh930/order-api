package me.devksh930.orderapi.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.AccountGender;

@Getter
@Slf4j
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class AccountLastOrderResponse {
    private Long id;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;
    private AccountGender accountGender;

    private AdminOrderInfoResponse lastOrderInfo;

    public AccountLastOrderResponse(Long id, String name, String nickname, String phoneNumber, String email, AccountGender accountGender) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountGender = accountGender;
    }

    public void orderInfoAdd(AdminOrderInfoResponse adminOrderInfoResponse) {
        this.lastOrderInfo = adminOrderInfoResponse;
    }

    public static AccountLastOrderResponse of(Account account) {
        return new AccountLastOrderResponse(account.getId(), account.getName(), account.getNickname(), account.getPhoneNumber(), account.getEmail(), account.getAccountGender());
    }

}
