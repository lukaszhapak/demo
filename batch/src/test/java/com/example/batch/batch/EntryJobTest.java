package com.example.batch.batch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import com.example.AbstractIntegrationTest;
import com.example.batch.batch.exception.ProcessingException;
import com.example.batch.batch.starter.EntryBatchJobStarter;
import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryStatus;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class EntryJobTest extends AbstractIntegrationTest {

  @Autowired
  EntryBatchJobStarter entryBatchJobStarter;

  @BeforeEach
  void setUp() {
	reset(mockEntryResource);
	when(mockEntryResource.someBusinessLogic(any())).thenAnswer(invocation -> mockSomeBusinessLogic(invocation.getArgument(0)));
  }

  @Test
  @DisplayName("should start batch job and process entries")
  void shouldStartBatchJobAndProcessEntries() {
	// given
	List<Long> ids = Stream.of(entryRepository.save(createEntry(EntryStatus.REGISTERED)), entryRepository.save(createEntry(EntryStatus.REGISTERED)), entryRepository.save(createEntry(
			EntryStatus.REGISTERED)), entryRepository.save(createEntry(EntryStatus.REGISTERED)))
		.map(Entry::getId).collect(Collectors.toList());

	// when
	entryBatchJobStarter.startBatch();

	// then
	List<Entry> processedEntries = entryRepository.findAllById(ids);

	assertThat(processedEntries.stream().filter(x -> x.getId() % 2 != 0)).allMatch(entry -> entry.getStatus() == EntryStatus.COMPLETED);
	assertThat(processedEntries.stream().filter(x -> x.getId() % 2 == 0)).allMatch(entry -> entry.getStatus() == EntryStatus.FAILED);

	// clean up
	entryRepository.deleteAllById(ids);
  }

  private Entry mockSomeBusinessLogic(Entry entry) {
	if (entry.getId() % 2 == 0) {
	  throw new ProcessingException();
	}
	return entry;
  }
}
