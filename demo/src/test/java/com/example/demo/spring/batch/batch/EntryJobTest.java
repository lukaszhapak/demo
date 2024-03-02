package com.example.demo.spring.batch.batch;

import static com.example.demo.spring.batch.core.model.EntryErrorType.BUSINESS;
import static com.example.demo.spring.batch.core.model.EntryErrorType.SYSTEM;
import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED;
import static com.example.demo.spring.batch.core.model.EntryStatus.FAILED;
import static com.example.demo.spring.batch.core.model.EntryStatus.REGISTERED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.spring.batch.AbstractIntegrationTest;
import com.example.demo.spring.batch.batch.exception.BusinessProcessingException;
import com.example.demo.spring.batch.batch.exception.SystemProcessingException;
import com.example.demo.spring.batch.batch.starter.EntryBatchJobStarter;
import com.example.demo.spring.batch.core.model.Entry;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryJobTest extends AbstractIntegrationTest {

  @Autowired
  EntryBatchJobStarter entryBatchJobStarter;

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	when(entryResourceClient.processEntry(any())).thenAnswer(invocation -> processEntry(invocation.getArgument(0)));
	List<Long> ids = saveEntries(10, REGISTERED);

	// when
	entryBatchJobStarter.startBatch();

	// then
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getStatus() == COMPLETED);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getErrorCode() == null);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getErrorType() == null);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getProcessingAttempts().equals(1L));

	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 1)).allMatch(entry -> entry.getStatus() == FAILED);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 1)).allMatch(entry -> entry.getErrorType() == BUSINESS);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 1)).allMatch(entry -> entry.getErrorCode().equals("Invalid data"));
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getProcessingAttempts().equals(1L));

	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 2)).allMatch(entry -> entry.getStatus() == FAILED);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 2)).allMatch(entry -> entry.getErrorType() == SYSTEM);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 2)).allMatch(entry -> entry.getErrorCode().equals("Rest Failure"));
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getProcessingAttempts().equals(1L));

	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 3)).allMatch(entry -> entry.getStatus() == FAILED);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 3)).allMatch(entry -> entry.getErrorType() == SYSTEM);
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 3)).allMatch(entry -> entry.getErrorCode().equals("Test exception"));
	assertThat(processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)).allMatch(entry -> entry.getProcessingAttempts().equals(1L));
  }

  private Entry processEntry(Entry entry) {
	if (entry.getId() % 4 == 3) {
	  throw new RuntimeException("Test exception");
	} else if (entry.getId() % 4 == 2) {
	  throw new SystemProcessingException();
	} else if (entry.getId() % 4 == 1) {
	  throw new BusinessProcessingException();
	}
	return entry;
  }
}