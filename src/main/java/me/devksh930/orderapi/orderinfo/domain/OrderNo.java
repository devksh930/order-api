package me.devksh930.orderapi.orderinfo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderNo implements Serializable {
    @Column(name = "order_number")
    private String orderNumber;

    public OrderNo(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public static OrderNo createOrderNumber() {
        String yyMMdd = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        return new OrderNo(yyMMdd + ThreadLocalRandom.current().nextInt(10000, 999999));
    }
}
