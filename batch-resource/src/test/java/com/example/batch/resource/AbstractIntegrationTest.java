package com.example.batch.resource;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.commons.commons.RestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractIntegrationTest implements RestClient {

  @LocalServerPort
  protected int port;

  protected EntryDTO createEntryDTO() {
	EntryDTO entry = new EntryDTO();
	entry.setData("test-data");
	return entry;
  }
}
