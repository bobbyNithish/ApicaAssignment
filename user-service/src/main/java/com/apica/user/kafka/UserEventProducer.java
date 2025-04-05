package com.apica.user.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {

    private static final String TOPIC = "user-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendUserEvent(String eventMessage) {
        kafkaTemplate.send(TOPIC, eventMessage);
    }
}
