package com.example.batch.batch.processor;

import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryStatus;
import com.example.batch.resource.MockEntryResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class EntryProcessor implements ItemProcessor<Entry, Entry> {

  private final MockEntryResource mockEntryResource;

  @Override
  public Entry process(Entry entry) {
	log.debug("Processing Entry={}", entry);
	entry = mockEntryResource.someBusinessLogic(entry);

	entry.setStatus(EntryStatus.COMPLETED);
	entry.setProcessingAttempts(entry.getProcessingAttempts() + 1);
	entry.setErrorType(null);
	entry.setErrorCode(null);
	return entry;
  }
}