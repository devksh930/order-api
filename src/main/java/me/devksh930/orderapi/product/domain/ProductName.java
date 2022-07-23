package me.devksh930.orderapi.product.domain;

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
public class ProductName {
    public static final String REGEX = "^.{1,100}$";
    public static final String ERR_MSG = "상품명은 1글자이상 100글자 이하의 글자만 입력이 가능합니다.";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    public ProductName(final String productName) {
        if (!PATTERN.matcher(productName).matches()) {
            throw new IllegalStateException(ERR_MSG);
        }
        this.productName = productName;
    }
}
