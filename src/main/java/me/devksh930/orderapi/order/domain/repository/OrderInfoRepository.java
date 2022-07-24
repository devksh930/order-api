package me.devksh930.orderapi.order.domain.repository;

import me.devksh930.orderapi.order.domain.OrderInfo;
import me.devksh930.orderapi.order.domain.OrderNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, OrderNo> {
    @Query(value = "SELECT oi FROM OrderInfo oi JOIN FETCH oi.orderer JOIN FETCH oi.product WHERE oi.id=:orderNo")
    Optional<OrderInfo> findById(OrderNo orderNo);
}
