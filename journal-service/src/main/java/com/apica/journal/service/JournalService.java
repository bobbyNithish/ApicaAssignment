package com.apica.journal.service;

import com.apica.journal.model.JournalEntry;
import com.apica.journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public JournalEntry saveEntry(String eventMessage) {
        JournalEntry entry = new JournalEntry(eventMessage, LocalDateTime.now());
        return journalRepository.save(entry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }
}
