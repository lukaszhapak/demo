package com.example.batch.core.controller;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.AbstractIntegrationTest;
import com.example.batch.core.model.EntryDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntryControllerTest extends AbstractIntegrationTest {

  private final String URL = "/batch/api/entry";

  @Test
  @DisplayName("should post entry")
  void shouldPostEntry() {
	// given
	EntryDTO entryDTO = createEntryDTO();

	// when
	Response response = postHttpCall(entryDTO, URL, port);
	EntryDTO responseAsEntryDTO = response.as(EntryDTO.class);

	// then
	assertThat(response.statusCode()).isEqualTo(SC_OK);
	assertThat(responseAsEntryDTO.getId()).isNotNull();
  }

  private EntryDTO createEntryDTO() {
	EntryDTO entryDTO = new EntryDTO();
	entryDTO.setData("test-data");
	return entryDTO;
  }
}
