package me.devksh930.orderapi.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class TokenResponse {
    @Schema(description = "JWT 토큰")
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
