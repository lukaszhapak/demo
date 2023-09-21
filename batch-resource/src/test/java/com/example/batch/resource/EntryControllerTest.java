package com.example.batch.resource;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntryControllerTest extends AbstractIntegrationTest {

  private final String URL = "/api/entry/process";

  @Test
  @DisplayName("should post entry")
  void shouldPostEntry() {
	// given
	EntryDTO entryDTO = createEntryDTO();

	// when
	Response httpResponse = postHttpCall(entryDTO, URL, port);
	ProcessResponseDTO response = httpResponse.as(ProcessResponseDTO.class);

	// then
	assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
	assertThat(response.getResponse()).isEqualTo("processed");
  }

  @Test
  @DisplayName("should return business exception")
  void shouldReturnBusinessException() {
	// given
	EntryDTO entryDTO = createEntryDTO();
	entryDTO.setData("business-exception");

	// when
	Response httpResponse = postHttpCall(entryDTO, URL, port);

	// then
	assertThat(httpResponse.statusCode()).isEqualTo(SC_BAD_REQUEST);
  }

  @Test
  @DisplayName("should return system exception")
  void shouldReturnSystemException() {
	// given
	EntryDTO entryDTO = createEntryDTO();
	entryDTO.setData("system-exception");

	// when
	Response httpResponse = postHttpCall(entryDTO, URL, port);

	// then
	assertThat(httpResponse.statusCode()).isEqualTo(SC_INTERNAL_SERVER_ERROR);
  }
}
