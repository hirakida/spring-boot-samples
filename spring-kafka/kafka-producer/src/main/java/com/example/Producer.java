package com.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, User> kafkaTemplate;

    public ListenableFuture<SendResult<String, User>> sendDefault(String key, User user) {
        return kafkaTemplate.sendDefault(key, user);
    }

    public ListenableFuture<SendResult<String, User>> send(String topic, String key, User user) {
        return kafkaTemplate.send(topic, key, user);
    }
}