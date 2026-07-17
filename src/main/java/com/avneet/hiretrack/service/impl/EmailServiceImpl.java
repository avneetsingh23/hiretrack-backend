package com.avneet.hiretrack.service.impl;

import com.avneet.hiretrack.service.EmailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to,
                          String subject,
                          String body) {

        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setFrom(
                    new InternetAddress(
                            "yourgmail@gmail.com",
                            "HireTrack Careers"
                    )
            );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed");
        }
    }
}