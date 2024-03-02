package com.example.demo.spring.batch.config;


import com.example.demo.spring.batch.core.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntryRepository extends JpaRepository<Entry, Long> {

}
