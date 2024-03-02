package com.example.demo.spring.batch;

import static com.example.demo.spring.batch.core.model.EntryErrorType.SYSTEM;
import static com.example.demo.spring.batch.core.model.EntryStatus.FAILED;
import static com.example.demo.spring.batch.core.model.EntryStatus.REGISTERED;
import static org.mockito.Mockito.reset;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.demo.commons.RestClient;
import com.example.demo.spring.batch.batch.client.EntryResourceClient;
import com.example.demo.spring.batch.batch.processor.EntryProcessor;
import com.example.demo.spring.batch.config.TestConfig;
import com.example.demo.spring.batch.config.TestEntryRepository;
import com.example.demo.spring.batch.core.model.Entry;
import com.example.demo.spring.batch.core.model.EntryStatus;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
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

  @Autowired
  protected EntryProcessor entryProcessor;

  @BeforeEach
  void setUp() {
	reset(entryResourceClient);
	reset(entryProcessor);
  }

  protected List<Long> saveEntries(int count, EntryStatus status) {
	return IntStream.range(0, count).mapToObj(i -> entryRepository.save(createEntry(status)).getId()).collect(Collectors.toList());
  }

  protected Entry createEntry(EntryStatus status) {
	return Entry.builder()
		.data("test-data")
		.status(status)
		.errorType(status == FAILED ? SYSTEM : null)
		.processingAttemptsLimit(5L)
		.processingAttempts(status == REGISTERED ? 0L : 1L)
		.build();
  }
}
