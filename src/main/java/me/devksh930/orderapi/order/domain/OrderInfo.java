package me.devksh930.orderapi.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.common.BaseTimeEntity;
import me.devksh930.orderapi.order.exception.NotMyOrderException;
import me.devksh930.orderapi.order.exception.PaymentAlreadyException;
import me.devksh930.orderapi.product.domain.Product;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
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
        if (this.payment != null) {
            return this.payment.getPaymentDate();
        }
        return null;
    }

    public void pay(Account account) {
        ordererCheck(account);
        if (isPayment()) {
            throw new PaymentAlreadyException();
        }
        this.payment = Payment.create();
    }

    public void ordererCheck(Account account) {
        if (!isMeOrder(account)) {
            throw new NotMyOrderException();
        }
    }

    private boolean isPayment() {
        return payment != null;
    }

    private boolean isMeOrder(Account account) {
        return this.orderer.equals(account);
    }


}
