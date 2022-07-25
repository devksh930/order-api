package me.devksh930.orderapi.order.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.devksh930.orderapi.order.domain.OrderInfo;
import me.devksh930.orderapi.order.dto.AdminOrderInfoResponse;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.devksh930.orderapi.account.domain.QAccount.account;
import static me.devksh930.orderapi.order.domain.QOrderInfo.orderInfo;
import static me.devksh930.orderapi.product.domain.QProduct.product;

@Repository
public class OrderRepositoryExtension extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryExtension(JPAQueryFactory queryFactory) {
        super(OrderInfo.class);
        this.queryFactory = queryFactory;
    }

    public List<AdminOrderInfoResponse> findAdminOrderInfoResponse(List<Long> ids) {
        return queryFactory.select(Projections.constructor(AdminOrderInfoResponse.class,
                        orderInfo.id.orderNumber,
                        orderInfo.product.productName.productName,
                        orderInfo.payment.paymentDate,
                        orderInfo.modifiedDate,
                        orderInfo.orderer.id))
                .from(orderInfo)
                .innerJoin(orderInfo.product, product)
                .innerJoin(orderInfo.orderer, account)
                .where(orderInfo.orderer.id.in(ids))
                .fetchAll().fetch();
    }
}
