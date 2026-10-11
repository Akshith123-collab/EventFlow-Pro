package com.efpm.service;

import com.efpm.model.Project;
import com.efpm.repository.ProjectRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired private JavaMailSender mailSender;
    @Autowired private ProjectRepository repo;

    @Value("${spring.mail.username}")
    private String defaultFrom;

    public void sendNotifications() {
        List<Project> projects = repo.findAll();

        for (Project p : projects) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                // If your Project model had PI email field, use it. For now use default or placeholder.
                helper.setTo(defaultFrom);
                helper.setSubject("EFPM Project Update Notification");
                helper.setText("Project: " + (p.getTitle() != null ? p.getTitle() : "Untitled") +
                        "\nStatus updates: " + (p.getStatusList() != null ? p.getStatusList().size() : 0) +
                        "\nPlease ensure all billing and status details are up to date.", false);
                mailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
