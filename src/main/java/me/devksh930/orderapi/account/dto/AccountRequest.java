package me.devksh930.orderapi.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import me.devksh930.orderapi.account.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Pattern;

@Getter
public class AccountRequest {

    @Schema(description = "이름",required = true)
    @Pattern(regexp = Name.REGEX,message = Name.ERR_MSG)
    private String name;

    @Schema(description = "닉네임",required = true)
    @Pattern(regexp = Nickname.REGEX,message = Nickname.ERR_MSG)
    private String nickname;

    @Schema(description = "비밀번호",required = true)
    @Pattern(regexp = Password.REGEX,message = Password.ERR_MSG)
    private String password;

    @Schema(description = "전화번호",required = true)
    @Pattern(regexp = PhoneNumber.REGEX,message = PhoneNumber.ERR_MSG)
    private String phoneNumber;

    @Schema(description = "이메일",required = true)
    @Pattern(regexp = Email.REGEX,message = Email.ERR_MSG)
    private String email;

    @Schema(description = "성별",required = false)
    private String accountGender;

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
