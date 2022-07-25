package me.devksh930.orderapi.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdminOrderInfoResponse {
    private  String orderNumber;
    private  String productName;
    private  boolean payment;
    private  ZonedDateTime paymentDate;
    private  LocalDateTime orderDate;
    private  Long accountId;

    public AdminOrderInfoResponse(String orderNumber, String productName, ZonedDateTime paymentDate, LocalDateTime orderDate, Long accountId) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.paymentDate = paymentDate;
        this.payment = paymentDate != null;
        this.orderDate = orderDate;
        this.accountId = accountId;
    }

}
