package me.devksh930.orderapi.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdminOrderInfoResponse {

    @Schema(description = "주문번호")
    private String orderNumber;
    @Schema(description = "상품명")
    private String productName;
    @Schema(description = "결제여부")
    private boolean payment;
    @Schema(description = "결제시간 UTC 표준시로 표시")
    private ZonedDateTime paymentDate;
    @Schema(description = "주문한 시간")
    private LocalDateTime orderDate;
    @JsonIgnore
    private Long accountId;

    public AdminOrderInfoResponse(String orderNumber, String productName, ZonedDateTime paymentDate, LocalDateTime orderDate, Long accountId) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.paymentDate = paymentDate;
        this.payment = paymentDate != null;
        this.orderDate = orderDate;
        this.accountId = accountId;
    }

}
