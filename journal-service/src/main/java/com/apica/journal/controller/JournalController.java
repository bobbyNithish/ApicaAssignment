package com.apica.journal.controller;

import com.apica.journal.model.JournalEntry;
import com.apica.journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journals")
public class JournalController {

    @Autowired
    private JournalRepository journalRepository;

    // Get all journal entries
    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return journalRepository.findAll();
    }

    // Optional: Get journal entry by ID
    @GetMapping("/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return journalRepository.findById(id).orElse(null);
    }
}
