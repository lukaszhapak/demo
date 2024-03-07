package com.example.demo.spring.batch.batch.processor;

import com.example.demo.spring.batch.batch.exception.BusinessProcessingException;
import com.example.demo.spring.batch.batch.exception.SystemProcessingException;
import com.example.demo.spring.batch.core.model.Entry;
import com.example.demo.spring.batch.batch.client.EntryResourceClient;
import com.example.demo.spring.batch.core.model.EntryErrorType;
import com.example.demo.spring.batch.core.model.EntryStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
@RequiredArgsConstructor
public class EntryProcessor implements ItemProcessor<Entry, Entry> {

  private final EntryResourceClient entryResourceClient;

  @Override
  public Entry process(Entry entry) {
	log.debug("Processing Entry={}", entry);
	try {
	  entry.setProcessingAttempts(entry.getProcessingAttempts() + 1);
	  entry = entryResourceClient.processEntry(entry);
	  updateEntry(entry, EntryStatus.COMPLETED, null, null);
	} catch (BusinessProcessingException exception) {
	  log.warn("Business processing exception while processing Entry={}", entry);
	  updateEntry(entry, EntryStatus.FAILED, EntryErrorType.BUSINESS, "Invalid data");
	} catch (SystemProcessingException exception) {
	  log.error("System processing exception while processing Entry={}", entry);
	  updateEntry(entry, EntryStatus.FAILED, EntryErrorType.SYSTEM, "Rest Failure");
	} catch (Exception exception) {
	  log.error("Other exception {}", exception.getClass());
	  updateEntry(entry, EntryStatus.FAILED, EntryErrorType.SYSTEM, exception.getMessage());
	}
	return entry;
  }

  private void updateEntry(Entry entry, EntryStatus status, EntryErrorType errorType, String errorCode) {
	entry.setStatus(status);
	entry.setErrorType(errorType);
	entry.setErrorCode(errorCode);
  }
}