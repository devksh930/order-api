package me.devksh930.orderapi.auth.dto;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
