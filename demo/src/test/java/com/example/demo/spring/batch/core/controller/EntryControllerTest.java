package com.example.demo.spring.batch.core.controller;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.spring.batch.AbstractIntegrationTest;
import com.example.demo.spring.batch.core.model.EntryDTO;
import com.example.demo.spring.batch.core.service.EntryService;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EntryControllerTest extends AbstractIntegrationTest {

  private final String URL = "/api/entry";

  @Autowired
  private EntryService entryService;

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

  @Test
  @DisplayName("should get entry")
  void shouldGetEntry() {
	// given
	Long id = entryService.postEntry(createEntryDTO()).getId();

	// when
	Response response = getHttpCall(URL + "/" + id, port);
	EntryDTO responseAsEntryDTO = response.as(EntryDTO.class);

	// then
	assertThat(response.statusCode()).isEqualTo(SC_OK);
	assertThat(responseAsEntryDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(createEntryDTO());
  }


  private EntryDTO createEntryDTO() {
	EntryDTO entryDTO = new EntryDTO();
	entryDTO.setData("test-data");
	return entryDTO;
  }
}
