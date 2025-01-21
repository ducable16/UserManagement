package com.something.demo.factory.storage;

import com.something.demo.factory.request.BaseRequest;
import com.something.demo.factory.request.RedisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisStorageFactory implements StorageFactory {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void store(BaseRequest request) {
        if (request instanceof RedisRequest) {
            redisTemplate.opsForValue().set( ((RedisRequest) request).getSessionID(), (RedisRequest) request);
        }
    }
}
