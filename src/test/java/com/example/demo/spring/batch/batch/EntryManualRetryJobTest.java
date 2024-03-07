package com.example.demo.spring.batch.batch;

import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED;
import static com.example.demo.spring.batch.core.model.EntryStatus.FAILED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.spring.batch.AbstractBatchIntegrationTest;
import com.example.demo.spring.batch.batch.starter.EntryManualRetryBatchJobStarter;
import com.example.demo.spring.batch.core.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryManualRetryJobTest extends AbstractBatchIntegrationTest {

  @Autowired
  EntryManualRetryBatchJobStarter entryManualRetryBatchJobStarter;

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	when(entryResourceClient.processEntry(any())).thenAnswer(invocation -> processEntry(invocation.getArgument(0)));
	Long id = saveEntries(1, FAILED).get(0);

	// when
	entryManualRetryBatchJobStarter.startBatch(id);

	// then
	Entry processedEntry = entryRepository.findById(id).get();

	assertThat(processedEntry.getStatus()).isEqualTo(COMPLETED);
	assertThat(processedEntry.getErrorCode()).isNull();
	assertThat(processedEntry.getErrorType()).isNull();
	assertThat(processedEntry.getProcessingAttempts()).isEqualTo(2L);
  }

  protected Entry processEntry(Entry entry) {
	return entry;
  }
}
