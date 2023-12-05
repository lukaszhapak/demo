package com.example.batch.batch;

import static com.example.batch.core.model.EntryStatus.COMPLETED;
import static com.example.batch.core.model.EntryStatus.REGISTERED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.AbstractIntegrationTest;
import com.example.batch.batch.starter.BasicEntryBatchJobStarter;
import com.example.batch.core.model.Entry;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BasicEntryJobTest extends AbstractIntegrationTest {

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

	assertThat(processedEntries.stream().filter(x -> x.getId() < 9)).allMatch(entry -> entry.getErrorCode() == null);
	assertThat(processedEntries.stream().filter(x -> x.getId() < 9)).allMatch(entry -> entry.getErrorType() == null);
	assertThat(processedEntries.stream().filter(x -> x.getId() < 9)).allMatch(entry -> entry.getProcessingAttempts().equals(1L));
	assertThat(processedEntries.stream().filter(x -> x.getId() < 9)).allMatch(entry -> entry.getStatus() == COMPLETED);

	assertThat(processedEntries.stream().filter(x -> x.getId() >= 9)).allMatch(entry -> entry.getStatus() == REGISTERED);
	assertThat(processedEntries.stream().filter(x -> x.getId() >= 9)).allMatch(entry -> entry.getProcessingAttempts().equals(0L));
  }

  private Entry processEntry(Entry entry) {
	return entry;
  }
}