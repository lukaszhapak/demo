package com.example.demo.spring.batch.core.controller;

import com.example.demo.spring.batch.batch.starter.EntryManualRetryBatchJobStarter;
import com.example.demo.spring.batch.core.model.EntryDTO;
import com.example.demo.spring.batch.core.service.EntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EntryController {

  private final EntryService entryService;
  private final EntryManualRetryBatchJobStarter entryManualRetryBatchJobStarter;

  @PostMapping("/api/entry")
  public EntryDTO postEntry(@RequestBody EntryDTO entryDTO) {
	log.debug("posting entryDTO={}", entryDTO);
	return entryService.postEntry(entryDTO);
  }
  @GetMapping("/api/entry/{id}")
  public EntryDTO getEntry(@PathVariable Long id) {
	log.debug("getting entry by id={}", id);
	return entryService.findById(id);
  }

  @PostMapping("/api/entry/reprocess/{entryId}")
  public String reprocessEntry(@PathVariable Long entryId) {
	log.debug("reprocessing entryId={}", entryId);
	entryManualRetryBatchJobStarter.startBatch(entryId);
	return "reprocessing of entry with id = " + entryId + " has started";
  }
}
