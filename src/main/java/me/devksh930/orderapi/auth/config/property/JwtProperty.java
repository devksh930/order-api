package me.devksh930.orderapi.auth.config.property;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("app.jwt")
@Configuration
@Getter
public class JwtProperty {
    private String secretKey;
    private long tokenValidityTime;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setTokenValidityTime(long tokenValidityTime) {
        this.tokenValidityTime = tokenValidityTime;
    }
}
