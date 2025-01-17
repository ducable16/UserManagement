package com.something.demo.factory;

import com.something.demo.entity.ChatMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileStorage implements DataStorage {
    @Override
    public ResponseEntity<?> saveData(ChatMessage data) {
        try (FileWriter writer = new FileWriter("data.txt", true)) {
            writer.write(data + "\n");
            System.out.println("Data saved to file: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
}