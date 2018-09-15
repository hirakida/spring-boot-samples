package com.example.repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StringRepository {

    private final StringRedisTemplate stringRedisTemplate;

    public Optional<String> get(String key) {
        return stringRedisTemplate.hasKey(key)
               ? Optional.of(stringRedisTemplate.opsForValue().get(key))
               : Optional.empty();
    }

    public Boolean expire(String key, long seconds) {
        return stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue()
                           .set(key, value);
    }

    public void set(String key, String value, long seconds) {
        stringRedisTemplate.opsForValue()
                           .set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
}