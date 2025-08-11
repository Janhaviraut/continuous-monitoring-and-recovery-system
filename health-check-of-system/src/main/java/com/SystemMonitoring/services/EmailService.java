package com.SystemMonitoring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{

@Autowired
private JavaMailSender mailSender;

public void sendEmail(String subject, String content) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("janhaviraut80@gmail.com");
    message.setSubject(subject);
    message.setText(content);
    mailSender.send(message);
}

}