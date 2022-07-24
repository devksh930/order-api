package me.devksh930.orderapi.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Column(name = "payment_date")
    private ZonedDateTime paymentDate;

    public Payment(final ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public static Payment create() {
        return new Payment(ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC));
    }
}