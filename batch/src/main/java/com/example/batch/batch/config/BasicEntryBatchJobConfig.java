package com.example.batch.batch.config;

import com.example.batch.batch.client.EntryResourceClient;
import com.example.batch.batch.io.EntryReader;
import com.example.batch.batch.io.EntryWriter;
import com.example.batch.batch.processor.EntryProcessor;
import com.example.batch.batch.properties.BasicEntryBatchJobProperties;
import com.example.batch.core.model.Entry;
import com.example.batch.core.repository.EntryRepository;
import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BasicEntryBatchJobConfig {

  @Bean
  public EntryReader basicEntryReader(EntityManagerFactory entityManagerFactory, BasicEntryBatchJobProperties basicEntryBatchJobProperties) {
	String query = "SELECT entry FROM Entry as entry where entry.status='REGISTERED' order by entry.id ASC";
	EntryReader itemReader = new EntryReader();
	itemReader.setPageSize(basicEntryBatchJobProperties.getPageSize());
	itemReader.setQueryString(query);
	itemReader.setEntityManagerFactory(entityManagerFactory);
	return itemReader;
  }

  @Bean
  @StepScope
  public EntryProcessor basicEntryProcessor(EntryResourceClient entryResourceClient) {
	return new EntryProcessor(entryResourceClient);
  }

  @Bean
  @StepScope
  public EntryWriter basicEntryWriter(EntryRepository entryRepository) {
	return new EntryWriter(entryRepository);
  }

  @Bean
  public Step basicEntryProcessingStep(StepBuilderFactory stepBuilderFactory, ItemReader<Entry> basicEntryReader, ItemProcessor<Entry, Entry> basicEntryProcessor,
	  ItemWriter<Entry> basicEntryWriter, TaskExecutor entryProcessorTaskExecutor, BasicEntryBatchJobProperties basicEntryBatchJobProperties) {
	return stepBuilderFactory.get("basicEntryProcessingStep")
		.<Entry, Entry>chunk(basicEntryBatchJobProperties.getChunkSize())
		.reader(basicEntryReader)
		.processor(basicEntryProcessor)
		.writer(basicEntryWriter)
		.taskExecutor(entryProcessorTaskExecutor)
		.build();
  }

  @Bean
  public Job basicEntryBatchJob(JobBuilderFactory jobBuilderFactory, Step basicEntryProcessingStep) {
	return jobBuilderFactory.get("baiscEntryBatchJob")
		.preventRestart()
		.flow(basicEntryProcessingStep)
		.end()
		.build();
  }

}