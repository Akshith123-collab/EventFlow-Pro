package com.efpm.scheduler;

import com.efpm.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {

    @Autowired private NotificationService notificationService;

    // Every 15 days (15 * 24 * 60 * 60 * 1000 ms) -> 1296000000
    // For development you can change this to a smaller interval; production keep as is.
    @Scheduled(fixedRate = 1296000000)
    public void sendProjectUpdateNotifications() {
        notificationService.sendNotifications();
    }
}
