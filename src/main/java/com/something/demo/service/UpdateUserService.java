package com.something.demo.service;

import com.something.demo.entity.User;
import com.something.demo.repository.UserRepository;
import com.something.demo.request.CreateUserRequest;
import com.something.demo.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ResponseEntity<?> updateUser(
            @RequestBody UpdateUserRequest request) {

        User user = new User(request.getName(), request.getDateOfBirth(), request.getAge(), request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getRole(), request.getCreateDate());
        //hello xin chao
        return ResponseEntity.ok(userRepository.save(user));
        // goodbye tam biet
        // thankyou cam on
        // sorry xin loi
    }
}
