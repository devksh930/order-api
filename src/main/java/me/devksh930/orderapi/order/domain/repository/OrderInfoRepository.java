package me.devksh930.orderapi.order.domain.repository;

import me.devksh930.orderapi.order.domain.OrderInfo;
import me.devksh930.orderapi.order.domain.OrderNo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, OrderNo> {
}
