package com.something.demo.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.something.demo.entity.Book;
import com.something.demo.factory.request.RedisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public ResponseEntity<?> save(Book book) {
        redisTemplate.opsForValue().set(book.getId(), book);
        return ResponseEntity.ok().build();
    }

    public Book findById(Long id) {
        return (Book) redisTemplate.opsForValue().get(id);
    }
}
