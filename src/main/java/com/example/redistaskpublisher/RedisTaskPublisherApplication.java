package com.example.redistaskpublisher;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisTaskPublisherApplication {

    @Bean
    public StatefulRedisConnection getRedisConnection() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        return connection;
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisTaskPublisherApplication.class, args);
    }

}
