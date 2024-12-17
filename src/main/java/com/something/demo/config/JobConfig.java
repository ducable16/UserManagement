package com.something.demo.config;

import com.something.demo.entity.User;
import com.something.demo.repository.UserRepository;
import com.something.demo.service.BatchService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfig {

    @Autowired
    BatchService batchService;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Lazy
    public Job runJob(JobRepository jobRepository) {
        return new JobBuilder("MSTabcNEUser", jobRepository)
                .start(step(jobRepository))
                .build();
    }

    @Bean
    @Lazy
    public Step step(JobRepository jobRepository) {
        return new StepBuilder("csv-step", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(batchService.itemReader())
                .processor(batchService.itemProcessor())
                .writer(batchService.itemWriter())
                .build();
    }
}
