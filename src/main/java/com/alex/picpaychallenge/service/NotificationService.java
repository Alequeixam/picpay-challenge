package com.alex.picpaychallenge.service;

import com.alex.picpaychallenge.domain.notification.NotificationRequest;
import com.alex.picpaychallenge.domain.user.User;
import com.alex.picpaychallenge.exception.EmailServiceDownException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NotificationService {
    private final RestTemplate restTemplate;

    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        var notificationRequest = new NotificationRequest(message, email);

        ResponseEntity<String> notificationResponse = restTemplate
                .postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest, String.class);

        if ((notificationResponse.getStatusCode() != HttpStatus.OK)) {
            log.error("An error occurred related to email service status.");
            throw new EmailServiceDownException("Email notification service is down.");
        }
    }
}
