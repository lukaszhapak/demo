package com.example.batch.batch.config;

import com.example.batch.batch.tasklet.EntryManualRetryPreProcessingTasklet;
import com.example.batch.core.repository.EntryRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntryManualRetryBatchJobConfig {

  @Bean
  @StepScope
  public Tasklet entryManualRetryPreProcessingTasklet(EntryRepository entryRepository) {
	return new EntryManualRetryPreProcessingTasklet(entryRepository);
  }

  @Bean
  public Step entryManualRetryPreProcessingStep(final StepBuilderFactory stepBuilderFactory, final Tasklet entryManualRetryPreProcessingTasklet) {
	return stepBuilderFactory.get("entryManualRetryPreProcessingStep")
		.tasklet(entryManualRetryPreProcessingTasklet)
		.build();
  }

  @Bean
  public Job entryManualRetryBatchJob(final JobBuilderFactory jobBuilderFactory, final Step entryProcessingStep, final Step entryManualRetryPreProcessingStep, final Step entryPostProcessingStep,
	  final JobExecutionListener entryJobExecutionListener) {
	return jobBuilderFactory.get("entryManualRetryBatchJob")
		.preventRestart()
		.flow(entryManualRetryPreProcessingStep)
		.on(ExitStatus.NOOP.getExitCode()).end()
		.next(entryProcessingStep)
		.next(entryPostProcessingStep)
		.end()
		.listener(entryJobExecutionListener)
		.build();
  }

}