package com.example.batch.batch.processor;

import com.example.batch.core.model.Entry;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class EntryInitProcessor implements ItemProcessor<Entry, Entry> {

  @Override
  public Entry process(Entry entry) {
	log.debug("Init processing of entry ={}", entry);
	entry.setProcessingTime(LocalDateTime.now());
	return entry;
  }
}
