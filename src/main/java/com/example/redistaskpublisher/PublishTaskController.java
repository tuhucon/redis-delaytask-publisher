package com.example.redistaskpublisher;

import io.lettuce.core.ScoredValue;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;

@RestController
public class PublishTaskController {

    private String zorderName = "zorder";

    @Autowired
    StatefulRedisConnection<String, String> redisConnection;

    @GetMapping("/publish")
    public void publish(@RequestParam Integer count) {
        long start = System.nanoTime();
        long current = System.nanoTime();
        RedisCommands<String, String> command = redisConnection.sync();
        for (int i = 0; i < count; i++) {
            long score = current + 15_000_000_000L;
            String key = "task" + i;
            command.zadd(zorderName, score, key);
        }
        long duration = System.nanoTime() - start;
        System.out.println(NumberFormat.getInstance().format(duration));
    }
}
