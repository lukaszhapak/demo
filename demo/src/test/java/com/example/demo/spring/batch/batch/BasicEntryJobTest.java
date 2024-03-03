package com.example.demo.spring.batch.batch;

import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED;
import static com.example.demo.spring.batch.core.model.EntryStatus.REGISTERED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.spring.batch.AbstractBatchIntegrationTest;
import com.example.demo.spring.batch.batch.starter.BasicEntryBatchJobStarter;
import com.example.demo.spring.batch.core.model.Entry;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BasicEntryJobTest extends AbstractBatchIntegrationTest {

  @Autowired
  BasicEntryBatchJobStarter basicEntryBatchJobStarter;

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	when(entryResourceClient.processEntry(any())).thenAnswer(invocation -> processEntry(invocation.getArgument(0)));
	List<Long> ids = saveEntries(10, REGISTERED);

	// when
	basicEntryBatchJobStarter.startBatch();

	// then
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries.stream().filter(
		entry -> entry.getStatus() == COMPLETED
		&& entry.getProcessingAttempts().equals(1L)
		&& entry.getErrorType() == null
		&& entry.getErrorCode() == null
	).collect(Collectors.toList())).hasSize(8);

	assertThat(processedEntries.stream().filter(
		entry -> entry.getStatus() == REGISTERED
		&& entry.getProcessingAttempts().equals(0L))
		.collect(Collectors.toList())).hasSize(2);
  }

  private Entry processEntry(Entry entry) {
	return entry;
  }
}