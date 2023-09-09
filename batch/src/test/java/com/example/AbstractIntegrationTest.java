package com.example;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.batch.BatchApplication;
import com.example.batch.config.TestConfig;
import com.example.batch.config.TestEntryRepository;
import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
import com.example.batch.batch.client.EntryResourceClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
  protected TestEntryRepository entryRepository;

  @Autowired
  protected EntryResourceClient entryResourceClient;

  protected Entry createEntry(EntryStatus status) {
	Entry entry = new Entry();
	entry.setData("test-data");
	entry.setStatus(status);
	entry.setErrorType(EntryErrorType.SYSTEM);
	entry.setProcessingAttemptsLimit(5L);
	entry.setProcessingAttempts(0L);
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
