package com.example.batch.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class PostEntryJob {

  private final EntryResourceClient entryResourceClient;

  @Scheduled(cron = "${entry.post.cron}")
  void postEntry() {
	for (int i = 0; i < 100; i++) {
	  EntryDTO entryDTO = EntryDTO.builder()
		  .data("example-data")
		  .build();
	  log.debug("posting entry={}", entryDTO);
	  EntryDTO response = entryResourceClient.postEntry(entryDTO);
	}
  }
}
