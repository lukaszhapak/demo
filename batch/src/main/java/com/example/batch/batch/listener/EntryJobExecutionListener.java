package com.example.batch.batch.listener;

import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
import com.example.batch.core.repository.EntryRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
@RequiredArgsConstructor
public class EntryJobExecutionListener implements JobExecutionListener {

  private final EntryRepository entryRepository;

  @Override
  public void beforeJob(JobExecution jobExecution) {
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
	log.debug("Starting entry job execution listener");
	if (jobExecution.getStatus() == BatchStatus.FAILED) {
	  String originator = (String) jobExecution.getJobParameters().getParameters().get("originator").getValue();
	  int count = entryRepository.markProcessingFailed(originator, EntryStatus.STARTED, EntryStatus.FAILED, EntryErrorType.SYSTEM, "Batch global failure");
	  if (count > 0) {
		log.debug("{} entries marked as failed by originator = {}", count, originator);
	  }
	}
  }
}
