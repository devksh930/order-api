package me.devksh930.orderapi.order.service;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.order.domain.OrderInfo;
import me.devksh930.orderapi.order.domain.OrderNo;
import me.devksh930.orderapi.order.domain.repository.OrderInfoRepository;
import me.devksh930.orderapi.order.dto.OrderInfoResponse;
import me.devksh930.orderapi.product.domain.Product;
import me.devksh930.orderapi.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderInfoRepository orderInfoRepository;
    private final ProductRepository productRepository;

    public OrderInfoResponse createOrder(Account account, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        OrderInfo orderInfo = new OrderInfo(account, product);

        return OrderInfoResponse.of(orderInfoRepository.save(orderInfo));
    }

    public OrderInfoResponse payment(Account account, String orderId) {
        OrderInfo orderInfo = orderInfoRepository.findById(new OrderNo(orderId)).orElseThrow();
        orderInfo.pay(account);
        return OrderInfoResponse.of(orderInfo);
    }

    public OrderInfoResponse findOrderInfo(String orderId) {
        OrderInfo orderInfo = orderInfoRepository.findById(new OrderNo(orderId)).orElseThrow();
        return OrderInfoResponse.of(orderInfo);
    }
}
