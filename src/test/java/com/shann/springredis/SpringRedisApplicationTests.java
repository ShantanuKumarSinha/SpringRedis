package com.shann.springredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringRedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("testKey", "Hello, Redis!");

        var val = redisTemplate.opsForValue().get("testKey");
        assert val.equals("Hello, Redis!");
    }

}
