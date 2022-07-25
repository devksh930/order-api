package me.devksh930.orderapi.order.exception;

import me.devksh930.orderapi.common.ErrorCode;
import me.devksh930.orderapi.common.exception.BusinessException;

public class NotMyOrderException extends BusinessException {
    public NotMyOrderException() {
        super(ErrorCode.NOT_MY_ORDER);
    }
}
