package com.example.batch.core.controller;

import com.example.batch.batch.starter.EntryManualRetryBatchJobStarter;
import com.example.batch.core.model.EntryDTO;
import com.example.batch.core.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EntryController {

  private final EntryService entryService;
  private final EntryManualRetryBatchJobStarter entryManualRetryBatchJobStarter;

  @PostMapping("/api/entry")
  public EntryDTO postEntry(@RequestBody EntryDTO entryDTO) {
	return entryService.postEntry(entryDTO);
  }

  @PostMapping("/api/entry/reprocess/{entryId}")
  public String reprocessEntry(@PathVariable Long entryId) {
	entryManualRetryBatchJobStarter.startBatch(entryId);
	return "reprocessing of entry with id = " + entryId + " has started";
  }
}
