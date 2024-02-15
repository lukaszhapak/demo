package com.example.batch.batch.starter;


import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntryManualRetryBatchJobStarter {

  private final JobLauncher asyncJobLauncher;

  private final Job entryManualRetryBatchJob;

  public void startBatch(Long entryId) {
	log.debug("Starting manual retry batch job");
	JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
	jobParametersBuilder.addString("originator", "Job_" + UUID.randomUUID());
	jobParametersBuilder.addLong("entryId", entryId);
	try {
	  asyncJobLauncher.run(entryManualRetryBatchJob, jobParametersBuilder.toJobParameters());
	} catch (JobExecutionException e) {
	  throw new RuntimeException(e);
	}
  }
}
