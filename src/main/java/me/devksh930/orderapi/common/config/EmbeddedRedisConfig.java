package me.devksh930.orderapi.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class EmbeddedRedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() {
        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting("maxmemory 128M")
                .build();

        try {
            redisServer.start();
        } catch (Exception e) {
            log.error("REDIS START ERROR", e);
        }
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            log.info("REDIS STOP");
            redisServer.stop();
        }
    }

}
