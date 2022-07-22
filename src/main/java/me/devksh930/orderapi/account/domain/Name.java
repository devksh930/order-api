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
public class Name {
    public static final String REGEX = "^[a-zA-Z가-힇]{1,20}$";
    public static final String ERR_MSG = "이름에는 1~20자의 한글,영어대소문자만 사용 가능합니다.";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public Name(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(ERR_MSG);
        }
        this.name = name;
    }
}

