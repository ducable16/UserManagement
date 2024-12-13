package com.something.demo.controller;

import com.something.demo.entity.User;
import com.something.demo.request.CreateUserRequest;
import com.something.demo.request.DeleteUserRequest;
import com.something.demo.request.LoginRequest;
import com.something.demo.request.UpdateUserRequest;
import com.something.demo.service.CreateUserService;
import com.something.demo.service.DeleteUserService;
//import com.example.demo.service.LoginService;
import com.something.demo.service.LoginService;
import com.something.demo.service.UpdateUserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private CreateUserService createUserService;
    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(createUserService.createUser(request));
    }

    @GetMapping("/delete")
    public ResponseEntity<?> createUser(
            @RequestParam ObjectId _id) {
        return deleteUserService.deleteUser(_id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser (
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser (
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(updateUserService.updateUser(request));
    }
}
