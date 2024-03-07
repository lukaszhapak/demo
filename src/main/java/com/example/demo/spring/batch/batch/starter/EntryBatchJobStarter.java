package com.example.demo.spring.batch.batch.starter;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class EntryBatchJobStarter {

  private final JobLauncher syncJobLauncher;

  private final Job entryBatchJob;

  @Scheduled(cron = "${batch.entry.cron}")
  public void startBatch() {
	log.debug("Starting entry batch job");
	JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
	jobParametersBuilder.addString("originator", "Job_" + UUID.randomUUID());
	try {
	  syncJobLauncher.run(entryBatchJob, jobParametersBuilder.toJobParameters());
	} catch (JobExecutionException e) {
	  throw new RuntimeException(e);
	}
  }
}
