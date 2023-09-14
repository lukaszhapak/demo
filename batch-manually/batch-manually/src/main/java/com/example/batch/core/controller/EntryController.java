package com.example.batch.core.controller;

import com.example.batch.core.model.EntryDTO;
import com.example.batch.core.service.EntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EntryController {

  private final EntryService entryService;

  @PostMapping("/api/entry")
  public EntryDTO postEntry(@RequestBody EntryDTO entryDTO) {
	log.debug("posting entryDTO={}", entryDTO);
	return entryService.postEntry(entryDTO);
  }

  @PostMapping("/api/entry/reprocess/{entryId}")
  public String reprocessEntry(@PathVariable Long entryId) {
	log.debug("reprocessing entryId={}", entryId);
//	entryManualRetryBatchJobStarter.startBatch(entryId);
	return "reprocessing of entry with id = " + entryId + " has started";
  }
}
