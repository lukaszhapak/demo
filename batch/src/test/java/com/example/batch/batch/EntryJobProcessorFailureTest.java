package com.example.batch.batch;

import static com.example.batch.core.model.EntryErrorType.SYSTEM;
import static com.example.batch.core.model.EntryStatus.FAILED;
import static com.example.batch.core.model.EntryStatus.REGISTERED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;

import com.example.AbstractIntegrationTest;
import com.example.batch.batch.starter.EntryBatchJobStarter;
import com.example.batch.core.model.Entry;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryJobProcessorFailureTest extends AbstractIntegrationTest {

  @Autowired
  EntryBatchJobStarter entryBatchJobStarter;

  @BeforeEach
  void setUp() {
	reset(entryResourceClient);
	reset(entryProcessor);
	doThrow(new RuntimeException("test")).when(entryProcessor).process(any());
  }

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	List<Long> ids = saveEntries(10, REGISTERED);

	// when
	entryBatchJobStarter.startBatch();

	// then
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries).allMatch(entry -> entry.getStatus() == FAILED);
	assertThat(processedEntries).allMatch(entry -> entry.getErrorCode().equals("Processing failure"));
	assertThat(processedEntries).allMatch(entry -> entry.getErrorType().equals(SYSTEM));
  }
}