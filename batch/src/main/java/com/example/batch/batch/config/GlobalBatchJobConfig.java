package com.example.batch.batch.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class GlobalBatchJobConfig {

  @Bean
  public TaskExecutor entryProcessorTaskExecutor() {
	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	taskExecutor.setCorePoolSize(4);
	taskExecutor.setMaxPoolSize(8);
	taskExecutor.setQueueCapacity(50);
	return taskExecutor;
  }

  @Bean
  public TaskExecutor asyncJobLauncherTaskExecutor() {
	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	taskExecutor.setCorePoolSize(1);
	taskExecutor.setMaxPoolSize(4);
	taskExecutor.setQueueCapacity(10);
	taskExecutor.setKeepAliveSeconds(30);
	taskExecutor.setAllowCoreThreadTimeOut(true);
	return taskExecutor;
  }

  @Bean
  public TaskExecutor syncJobLauncherTaskExecutor() {
	return new SyncTaskExecutor();
  }

  @Bean
  public JobLauncher asyncJobLauncher(final JobRepository jobRepository, final TaskExecutor asyncJobLauncherTaskExecutor) {
	SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	jobLauncher.setJobRepository(jobRepository);
	jobLauncher.setTaskExecutor(asyncJobLauncherTaskExecutor);
	return jobLauncher;
  }

  @Bean
  public JobLauncher syncJobLauncher(final JobRepository jobRepository, final TaskExecutor syncJobLauncherTaskExecutor) {
	SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
	jobLauncher.setJobRepository(jobRepository);
	jobLauncher.setTaskExecutor(syncJobLauncherTaskExecutor);
	return jobLauncher;
  }


}
