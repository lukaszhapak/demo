package com.example.batch.api.controller;

import com.example.batch.api.dto.EntryDTO;
import com.example.batch.api.dto.ProcessResponseDTO;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EntryController {

  private final Random random = new Random();

  @PostMapping("/api/entry/process")
  public ProcessResponseDTO processEntry(@RequestBody EntryDTO entryDTO) {
	ProcessResponseDTO processResponseDTO = new ProcessResponseDTO();
	log.debug("request {}", entryDTO);
	int random = this.random.nextInt(10);
	if (random == 1) {
	  log.debug("response SystemProcessingException");
	  processResponseDTO.setResponse("SystemProcessingException");
	}
	if (entryDTO.getData().equals("invalid")) {
	  log.debug("response BusinessProcessingException");
	  processResponseDTO.setResponse("BusinessProcessingException");
	} else {
	  log.debug("response processed");
	  processResponseDTO.setResponse("processed");
	}
	return processResponseDTO;
  }
}
