package me.devksh930.orderapi.orderinfo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor
public class OrderInfo {
    @EmbeddedId
    private OrderNo id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account orderer;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    private ZonedDateTime paymentDate;

    @Builder
    public OrderInfo(Account orderer, String productName, ZonedDateTime paymentDate) {
        this.id = OrderNo.createOrderNumber();
        this.orderer = orderer;
        this.productName = productName;
        this.paymentDate = paymentDate;
    }
}
