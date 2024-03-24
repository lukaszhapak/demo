package com.example.demo.spring.batch;

import com.example.demo.spring.batch.batch.config.BasicEntryBatchJobConfig;
import com.example.demo.spring.batch.batch.config.EntryAutomaticRetryBatchJobConfig;
import com.example.demo.spring.batch.batch.config.EntryBatchJobConfig;
import com.example.demo.spring.batch.batch.config.EntryManualRetryBatchJobConfig;
import com.example.demo.spring.batch.batch.config.GlobalBatchJobConfig;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
// this
//@EnableConfigurationProperties({EntryBatchJobProperties.class, EntryBatchJobProcessorProperties.class, EntryBatchJobAsyncLauncherProperties.class, BasicEntryBatchJobProperties.class})
// or this
@ConfigurationPropertiesScan
@Import({EntryBatchJobConfig.class, GlobalBatchJobConfig.class, EntryManualRetryBatchJobConfig.class, EntryAutomaticRetryBatchJobConfig.class, BasicEntryBatchJobConfig.class})
public class BatchApplication {

  public static void main(String[] args) {
	SpringApplication.run(BatchApplication.class, args);
  }

}
