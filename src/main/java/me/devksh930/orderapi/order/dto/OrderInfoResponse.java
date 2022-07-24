package me.devksh930.orderapi.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.devksh930.orderapi.order.domain.OrderInfo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderInfoResponse {
    private final String orderNumber;
    private final String productName;
    private final Long ordererId;
    private final String orderEmail;
    private final boolean payment;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final ZonedDateTime paymentDate;
    private final LocalDateTime orderDate;

    public OrderInfoResponse(String orderNumber, String productName, Long ordererId, String orderEmail, ZonedDateTime paymentDate, LocalDateTime orderDate) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.ordererId = ordererId;
        this.orderEmail = orderEmail;
        this.paymentDate = paymentDate;
        this.payment = paymentDate != null;
        this.orderDate = orderDate;

    }


    public static OrderInfoResponse of(final OrderInfo orderInfo) {
        return new OrderInfoResponse(orderInfo.getId(),
                orderInfo.getProduct().getProductName(),
                orderInfo.getOrderer().getId(),
                orderInfo.getOrderer().getEmail(),
                orderInfo.getPayment(),
                orderInfo.getCreatedDate());
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public Long getOrdererId() {
        return ordererId;
    }

    public String getOrderEmail() {
        return orderEmail;
    }

    public boolean isPayment() {
        return payment;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getPaymentDate() {
        if (paymentDate != null) {
            return paymentDate.format(DateTimeFormatter.ISO_DATE_TIME);
        }
        return null;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
