package com.example.batch.config;

import com.example.batch.batch.client.EntryResourceClient;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@TestConfiguration
public class TestConfig {

  @Bean
  public EntryResourceClient entryResourceClient() {
	return Mockito.mock(EntryResourceClient.class);
  }

  @Bean
  public TaskExecutor asyncJobLauncherTaskExecutor() {
	return new SyncTaskExecutor();
  }
}
