package com.example.batch.resource;

import com.example.batch.api.dto.ProcessResponseDTO;
import com.example.batch.batch.exception.BusinessProcessingException;
import com.example.batch.batch.exception.SystemProcessingException;
import com.example.batch.core.model.Entry;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class EntryResourceClient {

  public Entry processEntry(Entry entry) {
	Response response = postHttpCall(entry.toDTO(), "http://localhost:8081/batch-resource/api/entry/process", 8081);
	ProcessResponseDTO processResponseDTO = response.as(ProcessResponseDTO.class);
	if (processResponseDTO.getResponse().equals("SystemProcessingException")) {
	  throw new SystemProcessingException();
	} else if (processResponseDTO.getResponse().equals("BusinessProcessingException")) {
	  throw new BusinessProcessingException();
	}
	return entry;
  }

  protected Response postHttpCall(Object body, String url, int port) {
	return RestAssured.given()
		.port(port)
		.body(body)
		.contentType(ContentType.JSON)
		.when()
		.post(url);
  }
}