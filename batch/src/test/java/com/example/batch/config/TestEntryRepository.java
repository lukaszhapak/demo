package com.example.batch.config;

import com.example.batch.core.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntryRepository extends JpaRepository<Entry, Long> {

}
