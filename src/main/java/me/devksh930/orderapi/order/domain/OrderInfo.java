package me.devksh930.orderapi.order.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.common.BaseTimeEntity;
import me.devksh930.orderapi.product.domain.Product;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
public class OrderInfo extends BaseTimeEntity {
    @EmbeddedId
    private OrderNo id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account orderer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private Payment payment;

    @Builder
    public OrderInfo(final Account orderer,
                     final Product product) {
        this.id = OrderNo.createOrderNumber();
        this.orderer = orderer;
        this.product = product;
    }

    public String getId() {
        return id.getOrderNumber();
    }

    public ZonedDateTime getPayment() {
        return payment.getPaymentDate();
    }
}
