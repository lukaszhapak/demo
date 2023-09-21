package com.example.batch.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
class EntryController {

  private final EntryService entryService;

  @PostMapping("/api/entry/process")
  ProcessResponseDTO processEntry(@RequestBody EntryDTO entryDTO) {
	return entryService.getProcessResponseDTO(entryDTO);
  }
}
