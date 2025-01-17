package com.something.demo.factory;

import com.something.demo.entity.ChatMessage;
import com.something.demo.entity.User;
import com.something.demo.repository.ChatMessageRepository;
import com.something.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DatabaseStorage implements DataStorage {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public ResponseEntity<?> saveData(ChatMessage data) {
        System.out.println("Saving data to database: " + data);
        return ResponseEntity.ok(chatMessageRepository.save(data));
    }
}
