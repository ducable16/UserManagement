package com.something.demo.notification_handler;

import com.something.demo.entity.Notification;
import com.something.demo.entity.Role;

public interface Observer {
    public Role getRole();
    public void update(Notification notification);
}
