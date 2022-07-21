package me.devksh930.orderapi.account.dto;

import lombok.Getter;
import me.devksh930.orderapi.account.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;

@Getter
public class AccountRequest {
    @Pattern(regexp = Name.REGEX,message = Name.ERR_MSG)
    private String name;
    @Pattern(regexp = Nickname.REGEX,message = Nickname.ERR_MSG)
    private String nickname;
    @Pattern(regexp = Password.REGEX,message = Password.ERR_MSG)
    private String password;
    @Pattern(regexp = PhoneNumber.REGEX,message = PhoneNumber.ERR_MSG)
    private String phoneNumber;
    @Pattern(regexp = Email.REGEX,message = Email.ERR_MSG)
    private String email;
    @Pattern(regexp = Name.REGEX,message = Name.ERR_MSG)
    private AccountGender accountGender;

    public Account toEntity(final PasswordEncoder passwordEncoder) {
        return Account.builder()
                .name(name)
                .nickname(nickname)
                .password(password)
                .passwordEncoder(passwordEncoder)
                .phoneNumber(phoneNumber)
                .email(email)
                .accountGender(accountGender)
                .build();
    }

}
