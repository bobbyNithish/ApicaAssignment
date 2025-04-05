package com.apica.journal.kafka;

import com.apica.journal.model.JournalEntry;
import com.apica.journal.repository.JournalRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @Autowired
    private JournalRepository journalRepository;

    @KafkaListener(topics = "user-events", groupId = "journal-group")
    public void consume(String message) {
        JournalEntry entry = new JournalEntry();
        entry.setEvent(message);
        entry.setTimestamp(LocalDateTime.now());
        journalRepository.save(entry);
        System.out.println("Consumed and saved: " + message);
    }

}
