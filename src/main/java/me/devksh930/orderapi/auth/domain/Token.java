package me.devksh930.orderapi.auth.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@RedisHash("token")
public class Token {
    @Id
    private Long accountId;

    private String token;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long tokenValidityTime;

    public Token(Long accountId, String token, Long tokenValidityTime) {
        this.accountId = accountId;
        this.token = token;
        this.tokenValidityTime = tokenValidityTime;
    }
}
