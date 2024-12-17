//package com.something.demo.controller;
//import com.something.demo.service.BatchService;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/batch")
//public class UserLoadController {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    private BatchService batchService;
//    @Autowired
//    JobRepository jobRepository;
//
//    // API để khởi chạy job batch
//    @PostMapping("/start")
//    public ResponseEntity<String> startBatchJob() {
//        try {
//            // Tạo JobParameters mới (tham số cho job)
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("time", System.currentTimeMillis()) // Tránh cache job execution
//                    .toJobParameters();
//
//            // Khởi chạy job và lấy thông tin execution
//            JobExecution jobExecution = jobLauncher.run(batchService.runJob(jobRepository), jobParameters);
//
//            // Trả về thông tin job execution
//            return ResponseEntity.ok("Job started with ID: " + jobExecution.getId()
//                    + ", Status: " + jobExecution.getStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Job failed to start: " + e.getMessage());
//        }
//    }
//}