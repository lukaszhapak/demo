package com.example.batch.core.service;

import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryDTO;
import com.example.batch.core.repository.EntryRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EntryService {

  private final EntryRepository entryRepository;

  public EntryDTO postEntry(EntryDTO entryDTO) {
	log.debug("posting entryDTO={}", entryDTO);
	return entryRepository.save(Entry.of(entryDTO)).toDTO();
  }

  public EntryDTO findById(Long id) {
	log.debug("finding by id={}", id);
	return entryRepository.findById(id).toDTO();
  }
}
