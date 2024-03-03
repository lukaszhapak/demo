package com.example.demo.spring.batch.batch;

import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED;
import static com.example.demo.spring.batch.core.model.EntryStatus.FAILED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.spring.batch.AbstractBatchIntegrationTest;
import com.example.demo.spring.batch.batch.starter.EntryAutomaticRetryBatchJobStarter;
import com.example.demo.spring.batch.core.model.Entry;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryAutomaticRetryJobTest extends AbstractBatchIntegrationTest {

  @Autowired
  EntryAutomaticRetryBatchJobStarter entryAutomaticRetryBatchJobStarter;

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	when(entryResourceClient.processEntry(any())).thenAnswer(invocation -> processEntry(invocation.getArgument(0)));
	List<Long> ids = saveEntries(10, FAILED);

	// when
	entryAutomaticRetryBatchJobStarter.startBatch();

	// then
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries).allMatch(entry -> entry.getStatus() == COMPLETED);
	assertThat(processedEntries).allMatch(entry -> entry.getErrorCode() == null);
	assertThat(processedEntries).allMatch(entry -> entry.getErrorType() == null);
	assertThat(processedEntries).allMatch(entry -> entry.getProcessingAttempts().equals(2L));
  }

  private Entry processEntry(Entry entry) {
	return entry;
  }
}
