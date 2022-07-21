package me.devksh930.orderapi.account.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    public static final String REGEX = "^(?=.{1,100}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String ERR_MSG = "이메일 형식이 올바르지 않습니다.";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    public Email(final String email) {
        if (!PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException(ERR_MSG);
        }
        this.email = email;
    }
}
