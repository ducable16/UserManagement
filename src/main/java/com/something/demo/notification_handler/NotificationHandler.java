package com.something.demo.notification_handler;

import com.something.demo.entity.Notification;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationHandler implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private Notification notification;

    @Autowired
    List<Observer> injectObservers;

    @PostConstruct
    public void initObservers() {
        observers.addAll(injectObservers);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }


    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            if(notification.getReceiver_roles().contains(o.getRole()))
                o.update(notification);
        }
    }
    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    @RabbitListener(queues = {"notification_queue"})
    public void receiveNewNotification(Notification notification) {
        setNotification(notification);
        notifyObservers();
    }
}
