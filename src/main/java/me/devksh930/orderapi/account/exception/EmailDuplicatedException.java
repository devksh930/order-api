package me.devksh930.orderapi.account.exception;

public class EmailDuplicatedException extends RuntimeException{
    public EmailDuplicatedException() {
        super("이미 가입된 메일 입니다.");
    }
}
