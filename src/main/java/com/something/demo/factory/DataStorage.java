package com.something.demo.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.something.demo.entity.ChatMessage;
import org.springframework.http.ResponseEntity;

public interface DataStorage {
    ResponseEntity<?> saveData(ChatMessage data);
}
