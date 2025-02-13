package com.something.demo.controller;

import com.something.demo.entity.Notification;
import com.something.demo.entity.Post;
import com.something.demo.factory.request.BaseRequest;
import com.something.demo.factory.request.DatabaseRequest;
import com.something.demo.factory.request.RedisRequest;
import com.something.demo.factory.storage.DatabaseStorageFactory;
import com.something.demo.factory.storage.RedisStorageFactory;
import com.something.demo.request.FilterRequest;
import com.something.demo.request.CreateUserRequest;
import com.something.demo.request.LoginRequest;
import com.something.demo.request.UpdateUserRequest;
import com.something.demo.service.*;
//import com.example.demo.service.LoginService;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    FilterService filterService;
    @Autowired
    DatabaseStorageFactory databaseStorageFactory;
    @Autowired
    RedisStorageFactory redisStorageFactory;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ElasticService elasticService;

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(
            @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(createUserService.createUser(request));
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(
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
    @PostMapping("/start-batch")
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
    @GetMapping("/filter")
    public ResponseEntity<?> filterUser (@RequestBody FilterRequest filterRequest) {
        return ResponseEntity.ok(filterService.findUserByFilter(filterRequest));
    }


    @PostMapping("distribute-data")
    public ResponseEntity<?> distributeData (@RequestBody BaseRequest request) {
        if(request instanceof RedisRequest) {
            redisStorageFactory.store((RedisRequest) request);
            return ResponseEntity.ok((RedisRequest) request);
        }
        else if(request instanceof DatabaseRequest) {
                databaseStorageFactory.store((DatabaseRequest) request);
                return ResponseEntity.ok((DatabaseRequest) request);
        }
        else return ResponseEntity.status(406).build();
    }
    @PostMapping("/notification")
    public ResponseEntity<?> sendMessage(@RequestBody Notification notification) {
        rabbitTemplate.convertAndSend("directExchange", "noti", notification);
        return ResponseEntity.ok(notification);
    }
    @PostMapping("/post")
    public ResponseEntity<?> savePost(@RequestBody Post post) {
        elasticService.savePost(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/search")
    public List<Post> searchPost (@RequestParam String text) {
        return elasticService.search(text);
    }

    @GetMapping("/all-post")
    public List<Post> getAllPost () {
        return elasticService.allPost();
    }
}
