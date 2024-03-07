package com.example.demo.spring.batch.batch.config;

import com.example.demo.spring.batch.batch.client.EntryResourceClient;
import com.example.demo.spring.batch.batch.exception.CustomSkipPolicy;
import com.example.demo.spring.batch.batch.io.EntryReader;
import com.example.demo.spring.batch.batch.io.EntryWriter;
import com.example.demo.spring.batch.batch.listener.EntryJobExecutionListener;
import com.example.demo.spring.batch.batch.processor.EntryInitProcessor;
import com.example.demo.spring.batch.batch.processor.EntryProcessor;
import com.example.demo.spring.batch.batch.properties.EntryBatchJobProperties;
import com.example.demo.spring.batch.batch.tasklet.EntryPreProcessingTasklet;
import com.example.demo.spring.batch.core.model.Entry;
import com.example.demo.spring.batch.core.repository.EntryRepository;
import java.util.Arrays;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class EntryBatchJobConfig {

  @Bean
  @StepScope
  public Tasklet entryPreProcessingTasklet(EntryRepository entryRepository) {
	return new EntryPreProcessingTasklet(entryRepository);
  }

  @Bean
  @StepScope
  public EntryReader entryReader(final EntityManagerFactory entityManagerFactory, final @Value("#{jobParameters['originator']}") String originator,
	  final EntryBatchJobProperties entryBatchJobProperties) {
	String query = "SELECT entry FROM Entry as entry where entry.originator = :originator order by entry.id ASC";
	Map<String, Object> params = Map.of("originator", originator);
	EntryReader itemReader = new EntryReader();
	itemReader.setPageSize(entryBatchJobProperties.getPageSize());
	itemReader.setQueryString(query);
	itemReader.setParameterValues(params);
	itemReader.setEntityManagerFactory(entityManagerFactory);
	itemReader.setSaveState(false);
	itemReader.setTransacted(false);
	return itemReader;
  }

  @Bean
  @StepScope
  public EntryInitProcessor entryInitProcessor() {
	return new EntryInitProcessor();
  }

  @Bean
  @StepScope
  public EntryProcessor entryProcessor(final EntryResourceClient entryResourceClient) {
	return new EntryProcessor(entryResourceClient);
  }

  @Bean
  @StepScope
  public EntryWriter entryWriter(final EntryRepository entryRepository) {
	return new EntryWriter(entryRepository);
  }

  @Bean
  public Step entryPreProcessingStep(final StepBuilderFactory stepBuilderFactory, final Tasklet entryPreProcessingTasklet) {
	return stepBuilderFactory.get("entryPreProcessingStep")
		.tasklet(entryPreProcessingTasklet)
		.build();
  }

  @Bean
  public Step entryProcessingStep(final StepBuilderFactory stepBuilderFactory, final ItemReader<Entry> entryReader, final ItemProcessor<Entry, Entry> entryProcessor,
	  final ItemWriter<Entry> entryWriter, final ItemProcessor<Entry, Entry> entryInitProcessor, final TaskExecutor entryProcessorTaskExecutor, final EntryBatchJobProperties entryBatchJobProperties) {
	CompositeItemProcessor<Entry, Entry> compositeItemProcessor = new CompositeItemProcessor<>();
	compositeItemProcessor.setDelegates(Arrays.asList(entryInitProcessor, entryProcessor));

	return stepBuilderFactory.get("entryProcessingStep")
		.<Entry, Entry>chunk(entryBatchJobProperties.getChunkSize())
		.reader(entryReader)
		.processor(compositeItemProcessor)
		.writer(entryWriter)
		.faultTolerant()
		.skipPolicy(new CustomSkipPolicy())
		.taskExecutor(entryProcessorTaskExecutor)
		.throttleLimit(entryBatchJobProperties.getThrottleLimit())
		.build();
  }

  @Bean
  public JobExecutionListener entryJobExecutionListener(final EntryRepository entryRepository) {
	return new EntryJobExecutionListener(entryRepository);
  }

  @Bean
  public Job entryBatchJob(final JobBuilderFactory jobBuilderFactory, final Step entryProcessingStep, final Step entryPreProcessingStep, final JobExecutionListener entryJobExecutionListener) {
	return jobBuilderFactory.get("entryBatchJob")
		.preventRestart()
		.flow(entryPreProcessingStep)
		.on(ExitStatus.NOOP.getExitCode()).end()
		.next(entryProcessingStep)
		.end()
		.listener(entryJobExecutionListener)
		.build();
  }

}