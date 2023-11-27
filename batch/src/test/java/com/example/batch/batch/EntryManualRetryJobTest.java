package com.example.batch.batch;

import static com.example.batch.core.model.EntryStatus.COMPLETED;
import static com.example.batch.core.model.EntryStatus.FAILED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.example.AbstractIntegrationTest;
import com.example.batch.batch.starter.EntryManualRetryBatchJobStarter;
import com.example.batch.core.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryManualRetryJobTest extends AbstractIntegrationTest {

  @Autowired
  EntryManualRetryBatchJobStarter entryManualRetryBatchJobStarter;

  @BeforeEach
  void setUp() {
	reset(entryResourceClient);
	when(entryResourceClient.processEntry(any())).thenAnswer(invocation -> processEntry(invocation.getArgument(0)));
  }

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	Long id = saveEntries(1, FAILED).get(0);

	// when
	entryManualRetryBatchJobStarter.startBatch(id);

	// then
	log.debug("test -- loading entries");
	Entry processedEntry = entryRepository.findById(id).get();

	assertThat(processedEntry.getStatus()).isEqualTo(COMPLETED);
  }

  protected Entry processEntry(Entry entry) {
	return entry;
  }
}
