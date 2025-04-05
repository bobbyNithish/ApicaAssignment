package com.apica.journal.repository;

import com.apica.journal.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {
	
}
