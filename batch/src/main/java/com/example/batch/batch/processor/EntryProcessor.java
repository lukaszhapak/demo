package com.example.batch.batch.processor;

import static com.example.batch.core.model.EntryErrorType.BUSINESS;
import static com.example.batch.core.model.EntryErrorType.SYSTEM;
import static com.example.batch.core.model.EntryStatus.COMPLETED;
import static com.example.batch.core.model.EntryStatus.FAILED;

import com.example.batch.batch.exception.BusinessProcessingException;
import com.example.batch.batch.exception.SystemProcessingException;
import com.example.batch.core.model.Entry;
import com.example.batch.resource.EntryResourceClient;
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
	  entry = entryResourceClient.processEntry(entry);
	  entry.setStatus(COMPLETED);
	  entry.setErrorType(null);
	  entry.setErrorCode(null);
	} catch (BusinessProcessingException exception) {
	  log.warn("Business processing exception while processing Entry={}", entry);
	  entry.setStatus(FAILED);
	  entry.setErrorType(BUSINESS);
	  entry.setErrorCode("Invalid data");
	} catch (SystemProcessingException exception) {
	  log.error("System processing exception while processing Entry={}", entry);
	  entry.setStatus(FAILED);
	  entry.setErrorType(SYSTEM);
	  entry.setErrorCode("Rest Failure");
	} catch (Exception exception) {
	  log.error("Other exception {}", exception.getClass());
	  entry.setStatus(FAILED);
	  entry.setErrorType(SYSTEM);
	  entry.setErrorCode(exception.getMessage());
	}
	entry.setProcessingAttempts(entry.getProcessingAttempts() + 1);

	return entry;
  }
}