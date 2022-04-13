package com.ingemark.productsservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    private RedisTemplate<String, Object> redisTemplate;
    private ListOperations<String, Object> listOperations;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.listOperations = redisTemplate.opsForList();
    }

    public void save(String key, Object object) {
        log.info("Saving to redis: " + key + ", " + object);
        this.listOperations.leftPush(key, object);
    }

    public Object get(String key) {
        return this.listOperations.leftPop(key);
    }

}