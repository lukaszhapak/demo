package com.example.batch.core.service;

import com.example.batch.core.model.Entry;
import com.example.batch.core.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntryService {

  private final EntryRepository entryRepository;

  public Entry postEntry(Entry entry) {
	return entryRepository.save(entry);
  }
}