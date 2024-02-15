package com.example.batch.batch.client;

import com.example.batch.batch.exception.BusinessProcessingException;
import com.example.batch.batch.exception.SystemProcessingException;
import com.example.batch.core.model.Entry;
import com.example.commons.commons.RestClient;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class EntryResourceClient implements RestClient {

  public Entry processEntry(Entry entry) {
	Response response = postHttpCall(entry.toDTO(), "http://localhost/batch-resource/api/entry/process", 8081);
	int statusCode = response.getStatusCode();
	if (statusCode >= 500 && statusCode < 600) {
	  throw new SystemProcessingException();
	} else if (statusCode >= 400 && statusCode < 500) {
	  throw new BusinessProcessingException();
	}
	return entry;
  }
}
