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
public class Nickname {
    public static final String REGEX = "^[a-z]{1,30}$";
    public static final String ERR_MSG = "별명에는 1~30자의 영어소문자만 사용 가능합니다.";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    public Nickname(final String nickname) {
        if (!PATTERN.matcher(nickname).matches()) {
            throw new IllegalArgumentException(ERR_MSG);
        }
        this.nickname = nickname;
    }
}
