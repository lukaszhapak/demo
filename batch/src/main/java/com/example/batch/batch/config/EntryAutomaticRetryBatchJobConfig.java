package com.example.batch.batch.config;

import com.example.batch.batch.tasklet.EntryAutomaticRetryPreProcessingTasklet;
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
public class EntryAutomaticRetryBatchJobConfig {

  @Bean
  @StepScope
  public Tasklet entryAutomaticRetryPreProcessingTasklet(final EntryRepository entryRepository) {
	return new EntryAutomaticRetryPreProcessingTasklet(entryRepository);
  }

  @Bean
  public Step entryAutomaticRetryPreProcessingStep(final StepBuilderFactory stepBuilderFactory, final Tasklet entryAutomaticRetryPreProcessingTasklet) {
	return stepBuilderFactory.get("entryAutomaticRetryPreProcessingStep")
		.tasklet(entryAutomaticRetryPreProcessingTasklet)
		.build();
  }

  @Bean
  public Job entryAutomaticRetryBatchJob(final JobBuilderFactory jobBuilderFactory, final Step entryAutomaticRetryPreProcessingStep, final Step entryProcessingStep,
	  final JobExecutionListener entryJobExecutionListener) {
	return jobBuilderFactory.get("entryAutomaticRetryBatchJob")
		.preventRestart()
		.flow(entryAutomaticRetryPreProcessingStep)
		.on(ExitStatus.NOOP.getExitCode()).end()
		.next(entryProcessingStep)
		.end()
		.listener(entryJobExecutionListener)
		.build();
  }
}