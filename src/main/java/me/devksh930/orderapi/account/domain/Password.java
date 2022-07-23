package me.devksh930.orderapi.account.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
    public static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{10,20}";
    public static final String ERR_MSG = "비밀번호는 10~20, 최소 하나의 영어소문자, 영어 대문자, 특수 문자, 숫자 이상 포함되어야 합니다.";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(nullable = false, name = "password",length = 20)
    private String password;

    Password(final String password, final PasswordEncoder passwordEncoder) {
        if (!PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException(ERR_MSG);
        }
        this.password = passwordEncoder.encode(password);
    }
}
