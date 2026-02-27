package com.shann.springredis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> clazz) {
        try {
            var obj = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(obj.toString(), clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public void set(String key, Object o, long ttl) {
        redisTemplate.opsForValue().set(key, o, ttl);
    }
}
