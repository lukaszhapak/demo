package com.example.demo.spring.batch.batch.io;

import com.example.demo.spring.batch.core.model.Entry;
import com.example.demo.spring.batch.core.repository.EntryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

@Slf4j
@RequiredArgsConstructor
public class EntryWriter implements ItemWriter<Entry> {

  private final EntryRepository entryRepository;

  @Override
  public void write(List<? extends Entry> entries) {
	log.debug("Writing {} entries={}",entries.size(), entries);
	entryRepository.saveAll(entries);
  }
}