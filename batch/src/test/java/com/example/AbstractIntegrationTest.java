package com.example;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.batch.BatchApplication;
import com.example.batch.batch.client.EntryResourceClient;
import com.example.batch.config.TestConfig;
import com.example.batch.config.TestEntryRepository;
import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
import com.example.commons.commons.RestClient;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import({BatchApplication.class, TestConfig.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractIntegrationTest implements RestClient {

  @LocalServerPort
  protected int port;

  @Autowired
  protected TestEntryRepository entryRepository;

  @Autowired
  protected EntryResourceClient entryResourceClient;

  protected List<Long> saveEntries(int count, EntryStatus status) {
	return IntStream.range(0, count).mapToObj(i -> entryRepository.save(createEntry(status)).getId()).collect(Collectors.toList());
  }

  protected Entry createEntry(EntryStatus status) {
	Entry entry = new Entry();
	entry.setData("test-data");
	entry.setStatus(status);
	entry.setErrorType(EntryErrorType.SYSTEM);
	entry.setProcessingAttemptsLimit(5L);
	entry.setProcessingAttempts(0L);
	return entry;
  }
}
