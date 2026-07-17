package com.avneet.hiretrack.service;

public interface EmailService {

    void sendEmail(String to,
                   String subject,
                   String body);

}