package me.devksh930.orderapi.order.controller;

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
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/products/{productId}")
    public ResponseEntity<OrderInfoResponse> order(@CurrentAccount Account account,
                                                   @PathVariable("productId") Long productId) {
        OrderInfoResponse orderInfoResponse = orderService.createOrder(account, productId);
        URI uri = URI.create("/api/orders/" + orderInfoResponse.getOrdererId());
        return ResponseEntity.created(uri).body(orderInfoResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderInfoResponse> orderInfo(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.findOrderInfo(orderId));
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<OrderInfoResponse> payment(@CurrentAccount Account account,
                                                     @PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.payment(account, orderId));
    }
}
