//NotificationService.java
package com.yourpackage.service;
public interface NotificationService {
    String sendNotification();
}

//EmailNotificationService.java
package com.yourpackage.service;
import org.springframework.stereotype.Service;
@Service("emailService")   
public class EmailNotificationService implements NotificationService {
    @Override
    public String sendNotification() {
        return "Email Notification Sent";
    }
}

//SMSNotificationService.java
package com.yourpackage.service;
import org.springframework.stereotype.Service;
@Service("smsService")   
public class SMSNotificationService implements NotificationService {
    @Override
    public String sendNotification() {
        return "SMS Notification Sent";
    }
}

//NotificationController.java (Using @Qualifier)
package com.yourpackage.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yourpackage.service.NotificationService;

@RestController
public class NotificationController {
    @Autowired
    @Qualifier("emailService")   
    private NotificationService notificationService;
    @GetMapping("/notify")
    public String sendNotification() {
        return notificationService.sendNotification();
    }
}
