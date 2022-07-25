package me.devksh930.orderapi.account.exception;

import me.devksh930.orderapi.common.ErrorCode;
import me.devksh930.orderapi.common.exception.BusinessException;

public class AccountNotFounException extends BusinessException {
    public AccountNotFounException() {
        super(ErrorCode.ACCOUNT_NOT_FOUND);
    }
}
