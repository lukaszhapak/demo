package com.example.batch.batch.processor;

import static com.example.batch.core.model.EntryErrorType.BUSINESS;
import static com.example.batch.core.model.EntryErrorType.SYSTEM;
import static com.example.batch.core.model.EntryStatus.COMPLETED;
import static com.example.batch.core.model.EntryStatus.FAILED;

import com.example.batch.batch.exception.BusinessProcessingException;
import com.example.batch.batch.exception.SystemProcessingException;
import com.example.batch.core.model.Entry;
import com.example.batch.batch.client.EntryResourceClient;
import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
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
	  updateEntry(entry, COMPLETED, null, null);
	} catch (BusinessProcessingException exception) {
	  log.warn("Business processing exception while processing Entry={}", entry);
	  updateEntry(entry, FAILED, BUSINESS, "Invalid data");
	} catch (SystemProcessingException exception) {
	  log.error("System processing exception while processing Entry={}", entry);
	  updateEntry(entry, FAILED, SYSTEM, "Rest Failure");
	} catch (Exception exception) {
	  log.error("Other exception {}", exception.getClass());
	  updateEntry(entry, FAILED, SYSTEM, exception.getMessage());
	}
	return entry;
  }

  private void updateEntry(Entry entry, EntryStatus status, EntryErrorType errorType, String errorCode) {
	entry.setStatus(status);
	entry.setErrorType(errorType);
	entry.setErrorCode(errorCode);
  }
}