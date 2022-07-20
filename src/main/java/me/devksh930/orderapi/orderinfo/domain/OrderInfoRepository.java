package me.devksh930.orderapi.orderinfo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, OrderNo> {
}
