package com.something.demo.controller;

import com.something.demo.entity.User;
import com.something.demo.request.CreateUserRequest;
import com.something.demo.request.DeleteUserRequest;
import com.something.demo.request.LoginRequest;
import com.something.demo.request.UpdateUserRequest;
import com.something.demo.service.*;
//import com.example.demo.service.LoginService;
import org.bson.types.ObjectId;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
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
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private BatchService batchService;
    @Autowired
    JobRepository jobRepository;

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
    @PostMapping("/start_batch")
    public ResponseEntity<String> startBatchJob() {
        try {
            // Tạo JobParameters mới (tham số cho job)
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // Tránh cache job execution
                    .toJobParameters();

            // Khởi chạy job và lấy thông tin execution
            JobExecution jobExecution = jobLauncher.run(batchService.runJob(jobRepository), jobParameters);

            // Trả về thông tin job execution
            return ResponseEntity.ok("Job started with ID: " + jobExecution.getId()
                    + ", Status: " + jobExecution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Job failed to start: " + e.getMessage());
        }
    }
}
