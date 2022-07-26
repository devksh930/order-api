package me.devksh930.orderapi.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //COMMON ERROR
    INVALID_INPUT_VALUE(400, "C001", "잘못된 값을 입력했습니다"),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않는 Method 입니다"),
    UNAUTHORIZED(401, "C003", "자격증명에 실패 하였습니다"),
    HANDLE_AUTHENTICATION_ENTRYPOINT(401, "C004", "로그인후 사용가능합니다."),

    HANDLE_ACCESS_DENIED(403, "C005", "지원하지 않는 권한입니다."),


    //ACCOUINT ERROR,
    EMAIL_DUPLICATION(400, "A001", "이미 존재하는 이메일 입니다."),
    ACCOUNT_NOT_FOUND(404,"A002" ,"찾을수 없는 회원 입니다." ),

    //PRODUCT ERROR
    PRODUCT_NOT_FOUND(404, "P001", "존재하지 않는 상품입니다."),

    //ORDER
    PAYMENT_ALREADY(400, "O001", "이미 결제한 상품입니다"),
    NOT_MY_ORDER(400, "O002", "내가 주문항 상품이 아닙니다"),
    ORDER_NOT_FOUND(404,"O003" ,"주문 정보를 찾을수 없습니다" );

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
    }
