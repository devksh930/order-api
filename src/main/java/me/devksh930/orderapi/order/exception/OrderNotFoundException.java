package me.devksh930.orderapi.order.exception;

import me.devksh930.orderapi.common.ErrorCode;
import me.devksh930.orderapi.common.exception.BusinessException;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}
