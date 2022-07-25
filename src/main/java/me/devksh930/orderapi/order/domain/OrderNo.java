package me.devksh930.orderapi.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

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

        return new OrderNo(RandomString.make(20));
    }
}
