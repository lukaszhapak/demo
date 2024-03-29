package com.example.demo.spring.batch.config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import com.example.demo.spring.batch.batch.client.EntryResourceClient;
import com.example.demo.spring.batch.batch.processor.EntryProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@TestConfiguration
public class BatchTestConfig {

  @Bean
  public EntryResourceClient entryResourceClient() {
	return mock(EntryResourceClient.class);
  }

  @Bean
  public EntryProcessor entryProcessor(EntryResourceClient entryResourceClient) {
	EntryProcessor entryProcessor = new EntryProcessor(entryResourceClient);
	return spy(entryProcessor);
  }

  @Bean
  public TaskExecutor asyncJobLauncherTaskExecutor() {
	return new SyncTaskExecutor();
  }
}
