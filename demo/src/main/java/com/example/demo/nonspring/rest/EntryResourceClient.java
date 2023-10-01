package com.example.demo.nonspring.rest;

import com.example.demo.commons.RestClient;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class EntryResourceClient implements RestClient {

  Entry processEntry(Entry entry) {
	Response response = postHttpCall(entry, "http://localhost:8081/batch-resource/api/entry/process", 8081);
	int statusCode = response.getStatusCode();
	if (statusCode == 500) {
//	  throw new SystemProcessingException();
	} else if (statusCode == 400) {
//	  throw new BusinessProcessingException();
	}
	return entry;
  }
}
