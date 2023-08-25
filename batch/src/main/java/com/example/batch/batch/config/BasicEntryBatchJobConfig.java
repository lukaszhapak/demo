package com.example.batch.batch.config;

import com.example.batch.batch.io.EntryReader;
import com.example.batch.batch.io.EntryWriter;
import com.example.batch.batch.processor.EntryProcessor;
import com.example.batch.core.model.Entry;
import com.example.batch.core.repository.EntryRepository;
import com.example.batch.resource.EntryResourceClient;
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

public class BasicEntryBatchJobConfig {

  @Bean
  public EntryReader entryReader(EntityManagerFactory entityManagerFactory) {
	String query = "SELECT entry FROM Entry as entry order by entry.id ASC";
	EntryReader itemReader = new EntryReader();
	itemReader.setPageSize(2);
	itemReader.setQueryString(query);
	itemReader.setEntityManagerFactory(entityManagerFactory);
	return itemReader;
  }

  @Bean
  @StepScope
  public EntryProcessor entryProcessor(EntryResourceClient entryResourceClient) {
	return new EntryProcessor(entryResourceClient);
  }

  @Bean
  @StepScope
  public EntryWriter entryWriter(EntryRepository entryRepository) {
	return new EntryWriter(entryRepository);
  }

  @Bean
  public Step entryProcessingStep(StepBuilderFactory stepBuilderFactory, ItemReader<Entry> entryReader, ItemProcessor<Entry, Entry> entryProcessor, ItemWriter<Entry> entryWriter) {
	return stepBuilderFactory.get("entryProcessingStep")
		.<Entry, Entry>chunk(2)
		.reader(entryReader)
		.processor(entryProcessor)
		.writer(entryWriter)
		.build();
  }

  @Bean
  public Job entryBatchJob(JobBuilderFactory jobBuilderFactory, Step entryProcessingStep) {
	return jobBuilderFactory.get("entryBatchJob")
		.preventRestart()
		.flow(entryProcessingStep)
		.end()
		.build();
  }

}