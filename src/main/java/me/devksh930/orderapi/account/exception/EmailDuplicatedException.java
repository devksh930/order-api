package me.devksh930.orderapi.account.exception;

import me.devksh930.orderapi.common.ErrorCode;
import me.devksh930.orderapi.common.exception.BusinessException;

public class EmailDuplicatedException extends BusinessException {
    public EmailDuplicatedException() {
        super(ErrorCode.EMAIL_DUPLICATION);
    }
}
