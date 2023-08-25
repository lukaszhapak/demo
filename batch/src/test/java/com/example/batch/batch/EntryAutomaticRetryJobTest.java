package com.example.batch.batch;

import static com.example.batch.core.model.EntryStatus.COMPLETED;
import static com.example.batch.core.model.EntryStatus.FAILED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.example.AbstractIntegrationTest;
import com.example.batch.core.exception.SystemProcessingException;
import com.example.batch.batch.starter.EntryAutomaticRetryBatchJobStarter;
import com.example.batch.core.model.Entry;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryAutomaticRetryJobTest extends AbstractIntegrationTest {

  @Autowired
  EntryAutomaticRetryBatchJobStarter entryAutomaticRetryBatchJobStarter;

  @BeforeEach
  void setUp() {
	reset(entryResourceClient);
	when(entryResourceClient.processEntry(any())).thenAnswer(invocation -> processEntry(invocation.getArgument(0)));
  }

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	List<Long> ids = Stream.of(entryRepository.save(createEntry(FAILED)), entryRepository.save(createEntry(FAILED)), entryRepository.save(createEntry(FAILED)),
			entryRepository.save(createEntry(FAILED)))
		.map(Entry::getId).collect(Collectors.toList());

	// when
	entryAutomaticRetryBatchJobStarter.startBatch();

	// then
	log.debug("test -- loading entries");
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries.stream().filter(x -> x.getId() % 2 != 0)).allMatch(entry -> entry.getStatus() == COMPLETED);
	assertThat(processedEntries.stream().filter(x -> x.getId() % 2 == 0)).allMatch(entry -> entry.getStatus() == FAILED);

	// clean up
	entryRepository.deleteAllById(ids);
  }

  private Entry processEntry(Entry entry) {
	if (entry.getId() % 2 == 0) {
	  throw new SystemProcessingException();
	}
	return entry;
  }
}
