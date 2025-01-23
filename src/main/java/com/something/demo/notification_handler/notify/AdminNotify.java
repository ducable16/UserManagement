package com.something.demo.notification_handler.notify;

import com.something.demo.entity.Notification;
import com.something.demo.entity.Role;
import com.something.demo.entity.User;
import com.something.demo.notification_handler.NotifyElement;
import com.something.demo.notification_handler.Observer;
import com.something.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminNotify implements NotifyElement, Observer {
    private Notification notification;
    @Autowired
    UserRepository userRepository;

    @Override
    public void sendNotification() {
        List<User> admins = userRepository.findAllByRole(Role.ADMIN);
        for (int i = 0; i < admins.size(); i++) {
            System.out.println("Sending notification to ADMIN " + admins.get(i).getName() + " " + String.valueOf(i));
        }
    }

    @Override
    public void update(Notification notification) {
        this.notification = notification;
        sendNotification();
    }
    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
