package com.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.batch.BatchApplication;
import com.example.batch.batch.exception.ProcessingException;
import com.example.batch.resource.MockEntryResource;
import com.example.batch.config.TestConfig;
import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
import com.example.batch.core.repository.EntryRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@Import({BatchApplication.class, TestConfig.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractIntegrationTest {

  @LocalServerPort
  protected int port;

  @Autowired
  protected EntryRepository entryRepository;

  @Autowired
  protected MockEntryResource mockEntryResource;

  @BeforeEach
  void setUp() {
	reset(mockEntryResource);
	when(mockEntryResource.someBusinessLogic(any())).thenAnswer(invocation -> mockSomeBusinessLogic(invocation.getArgument(0)));
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

  protected Entry mockSomeBusinessLogic(Entry entry) {
	if (entry.getId() % 2 == 0) {
	  throw new ProcessingException();
	}
	return entry;
  }

  protected Response postHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post(url);
  }
}
