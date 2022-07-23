package me.devksh930.orderapi.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //COMMON ERROR
    INVALID_INPUT_VALUE(400, "C001", "잘못된 값을 입력했습니다"),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않는 Method 입니다"),
    UNAUTHORIZED(401, "C003", "자격증명에 실패 하였습니다"),
    HANDLE_AUTHENTICATION_ENTRYPOINT(401, "C004", "로그인후 사용가능합니다."),

    //ACCOUINT ERROR,
    EMAIL_DUPLICATION(400, "A001", "이미 존재하는 이메일 입니다.");


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
