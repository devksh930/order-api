package me.devksh930.orderapi.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import me.devksh930.orderapi.order.domain.OrderInfo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderInfoResponse {
    @Schema(description = "주문번호")
    private final String orderNumber;
    @Schema(description = "상품명")
    private final String productName;
    @Schema(description = "주문한 사람의 DB상의 id")
    private final Long ordererId;
    @Schema(description = "주문한 사람의 email")
    private final String orderEmail;
    @Schema(description = "결제여부")
    private final boolean payment;
    @Schema(description = "결제시간 UTC 표준시로 표시")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final ZonedDateTime paymentDate;
    @Schema(description = "주문한 시간")
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
