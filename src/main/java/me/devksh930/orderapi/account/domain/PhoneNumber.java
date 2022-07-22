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
public class PhoneNumber {
    public static final String REGEX = "^\\d{9,20}$";
    public static final String ERR_MSG = "전화번호는 9자리 이상 20자리의 숫자만 입력이 가능합니다";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    public PhoneNumber(final String phoneNumber) {
        if (!PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException(ERR_MSG);
        }
        this.phoneNumber = phoneNumber;
    }
}
