package com.something.demo.task;

import com.something.demo.entity.User;
import com.something.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.System.currentTimeMillis;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "1 * * * * ?")
    public void scheduleTaskWithFixedRate() {
        List<User> allUser = userRepository.findAll();
        for(var user : allUser) {
            user.setAge(user.getAge() + 1);
        }
        userRepository.saveAll(allUser);
        System.out.println("Users's age are updated");
    }

    public void scheduleTaskWithFixedDelay() {
    }

    public void scheduleTaskWithInitialDelay() {
    }

    public void scheduleTaskWithCronExpression() {
    }
}