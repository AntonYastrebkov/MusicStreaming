package com.music.streaming.service;

public interface MailService {
    void send(String email, String subject, String message);
}
