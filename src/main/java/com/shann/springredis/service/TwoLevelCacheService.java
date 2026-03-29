package com.shann.springredis.service;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TwoLevelCacheService {


    private RedisTemplate<String, Object> redisTemplate;

    private Cache localCache;

    private ObjectMapper objectMapper;

    public RedisService(RedisTemplate<String, Object> redisTemplate, CacheManager cacheManager, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.localCache = cacheManager.getCache("local");
        this.objectMapper = objectMapper;
    }

    // getFrom local cache
    public <T> T getFromLocalCache(String key, Class<T> clazz) {
        var valueWrapper = localCache.get(key);
        return valueWrapper == null ? null : getValueFromWrapper(clazz, valueWrapper.get());
    }

    // getFrom Redis
    public <T> T getFromRedis(String key, Class<T> clazz) {

        var obj = redisTemplate.opsForValue().get("user:" + key);
        return obj == null ? null : getValueFromWrapper(clazz, obj);
    }

    // Converting the value from the wrapper to the desired class type using ObjectMapper
    private <T> @Nullable T getValueFromWrapper(Class<T> clazz, Object obj) {
        try {
            return objectMapper.convertValue(obj, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public void set(String key, Object o, long ttl) {
        redisTemplate.opsForValue().set("user:" + key, o, ttl, TimeUnit.SECONDS);
    }
}
