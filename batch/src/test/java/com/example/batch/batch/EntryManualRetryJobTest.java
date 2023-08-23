package com.example.batch.batch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.example.AbstractIntegrationTest;
import com.example.batch.batch.starter.EntryManualRetryBatchJobStarter;
import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryStatus;
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
	reset(mockEntryResource);
	when(mockEntryResource.someBusinessLogic(any())).thenAnswer(invocation -> mockSomeBusinessLogic(invocation.getArgument(0)));
  }

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	Long id = entryRepository.save(createEntry(EntryStatus.FAILED)).getId();

	// when
	entryManualRetryBatchJobStarter.startBatch(id);

	// then
	log.debug("test -- loading entries");
	Entry processedEntry = entryRepository.findById(id).get();

	assertThat(processedEntry.getStatus()).isEqualTo(EntryStatus.COMPLETED);

	// clean up
	entryRepository.deleteById(id);
  }

  protected Entry mockSomeBusinessLogic(Entry entry) {
	return entry;
  }
}
