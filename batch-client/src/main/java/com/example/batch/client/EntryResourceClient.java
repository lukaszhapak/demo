package com.example.batch.client;

import com.example.commons.commons.RestClient;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
class EntryResourceClient implements RestClient {

  public EntryDTO postEntry(EntryDTO entry) {
	Response response = postHttpCall(entry, "http://localhost/batch/api/entry", 8080);
	int statusCode = response.getStatusCode();
	return entry;
  }
}
