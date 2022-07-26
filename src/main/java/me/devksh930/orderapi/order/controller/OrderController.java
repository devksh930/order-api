package me.devksh930.orderapi.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.auth.config.annotation.CurrentAccount;
import me.devksh930.orderapi.order.dto.OrderInfoResponse;
import me.devksh930.orderapi.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "주문,주문조회, 결제와 관련된 API")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "상품 주문")
    @PostMapping("/products/{productId}")
    public ResponseEntity<OrderInfoResponse> order(@CurrentAccount Account account,
                                                   @PathVariable("productId") Long productId) {
        OrderInfoResponse orderInfoResponse = orderService.createOrder(account, productId);
        URI uri = URI.create("/api/orders/" + orderInfoResponse.getOrdererId());
        return ResponseEntity.created(uri).body(orderInfoResponse);
    }

    @Operation(summary = "주문번호로 주문정보 조회")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderInfoResponse> orderInfo(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.findOrderInfo(orderId));
    }

    @Operation(summary = "주문번호로 결제")
    @PostMapping("/{orderId}")
    public ResponseEntity<OrderInfoResponse> payment(@Schema(hidden = true)
                                                     @CurrentAccount Account account,
                                                     @PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.payment(account, orderId));
    }
}
