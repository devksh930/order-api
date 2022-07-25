package me.devksh930.orderapi.admin.service;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.order.domain.repository.OrderInfoRepository;
import me.devksh930.orderapi.order.dto.OrderInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final OrderInfoRepository orderInfoRepository;

    public Page<OrderInfoResponse> getAccountOrderList(final PageRequest pageRequest, final Long accountId) {
        return  orderInfoRepository.findByOrdererId(accountId, pageRequest);
    }
}
