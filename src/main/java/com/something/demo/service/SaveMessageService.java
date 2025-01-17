package com.something.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.something.demo.entity.ChatMessage;
import com.something.demo.entity.User;
import com.something.demo.factory.DataStorage;
import com.something.demo.factory.DatabaseStorage;
import com.something.demo.factory.FileStorage;
import com.something.demo.repository.ChatMessageRepository;
import com.something.demo.repository.UserRepository;
import com.something.demo.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class SaveMessageService {
    @Autowired
    DatabaseStorage databaseStorage;
    @Autowired
    FileStorage fileStorage;

    public ResponseEntity<?> saveUser(
            @RequestBody ChatMessage request, @RequestParam String storage) throws JsonProcessingException {
        if(storage.equals("database")) {
            return ResponseEntity.ok(databaseStorage.saveData(request));
        }
        else {
            DataStorage dataStorage = new FileStorage();
            return ResponseEntity.ok(fileStorage.saveData(request));
        }
    }
}