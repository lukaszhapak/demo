package com.example.demo.spring.batch.batch.config;

import com.example.demo.spring.batch.batch.properties.EntryBatchJobAsyncLauncherProperties;
import com.example.demo.spring.batch.batch.properties.EntryBatchJobProcessorProperties;
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
  public TaskExecutor entryProcessorTaskExecutor(final EntryBatchJobProcessorProperties entryBatchJobProcessorProperties) {
	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	taskExecutor.setCorePoolSize(entryBatchJobProcessorProperties.getCorePoolSize());
	taskExecutor.setMaxPoolSize(entryBatchJobProcessorProperties.getMaxPoolSize());
	taskExecutor.setQueueCapacity(entryBatchJobProcessorProperties.getQueueCapacity());
	return taskExecutor;
  }

  @Bean
  public TaskExecutor asyncJobLauncherTaskExecutor(final EntryBatchJobAsyncLauncherProperties entryBatchJobAsyncLauncherProperties) {
	ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	taskExecutor.setCorePoolSize(entryBatchJobAsyncLauncherProperties.getCorePoolSize());
	taskExecutor.setMaxPoolSize(entryBatchJobAsyncLauncherProperties.getMaxPoolSize());
	taskExecutor.setQueueCapacity(entryBatchJobAsyncLauncherProperties.getQueueCapacity());
	taskExecutor.setKeepAliveSeconds(entryBatchJobAsyncLauncherProperties.getKeepAliveSeconds());
	taskExecutor.setAllowCoreThreadTimeOut(entryBatchJobAsyncLauncherProperties.getAllowCoreThreadTimeout());
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
