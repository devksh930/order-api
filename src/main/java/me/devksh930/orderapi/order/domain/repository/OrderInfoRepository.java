package me.devksh930.orderapi.order.domain.repository;

import me.devksh930.orderapi.order.domain.OrderInfo;
import me.devksh930.orderapi.order.domain.OrderNo;
import me.devksh930.orderapi.order.dto.OrderInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, OrderNo> {
    @Query(value = "SELECT oi FROM OrderInfo oi JOIN FETCH oi.orderer JOIN FETCH oi.product WHERE oi.id=:orderNo")
    Optional<OrderInfo> findById(OrderNo orderNo);

    @Query(value = "SELECT new me.devksh930.orderapi.order.dto.OrderInfoResponse(oi.id.orderNumber, " +
                                                                                "oi.product.productName.productName, " +
                                                                                "oi.orderer.id, " +
                                                                                "oi.orderer.email.email, " +
                                                                                "oi.payment.paymentDate, " +
                                                                                "oi.createdDate) " +
                    "FROM OrderInfo oi JOIN  oi.orderer JOIN  oi.product where oi.orderer.id=:accountId"
    ,countQuery = "SELECT count(oi) FROM OrderInfo oi WHERE oi.orderer.id=:accountId")
    Page<OrderInfoResponse> findByOrdererId(Long accountId, Pageable pageable);

}
