package me.devksh930.orderapi.order.exception;

import me.devksh930.orderapi.common.ErrorCode;
import me.devksh930.orderapi.common.exception.BusinessException;

public class PaymentAlreadyException extends BusinessException {
    public PaymentAlreadyException() {
        super(ErrorCode.PAYMENT_ALREADY);
    }
}
