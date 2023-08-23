package com.example.batch.batch;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.AbstractIntegrationTest;
import com.example.batch.batch.starter.EntryAutomaticRetryBatchJobStarter;
import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryStatus;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryAutomaticRetryJobTest extends AbstractIntegrationTest {

  @Autowired
  EntryAutomaticRetryBatchJobStarter entryAutomaticRetryBatchJobStarter;

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	List<Long> ids = Stream.of(entryRepository.save(createEntry(EntryStatus.FAILED)), entryRepository.save(createEntry(EntryStatus.FAILED)), entryRepository.save(createEntry(
			EntryStatus.FAILED)), entryRepository.save(createEntry(EntryStatus.FAILED)))
		.map(Entry::getId).collect(Collectors.toList());

	// when
	entryAutomaticRetryBatchJobStarter.startBatch();

	// then
	log.debug("test -- loading entries");
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries.stream().filter(x -> x.getId() % 2 != 0)).allMatch(entry -> entry.getStatus() == EntryStatus.COMPLETED);
	assertThat(processedEntries.stream().filter(x -> x.getId() % 2 == 0)).allMatch(entry -> entry.getStatus() == EntryStatus.FAILED);

	// clean up
	entryRepository.deleteAllById(ids);
  }
}
