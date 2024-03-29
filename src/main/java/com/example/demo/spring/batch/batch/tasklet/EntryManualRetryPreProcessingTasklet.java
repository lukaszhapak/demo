package com.example.demo.spring.batch.batch.tasklet;

import com.example.demo.spring.batch.core.repository.EntryRepository;
import com.example.demo.spring.batch.core.model.EntryStatus;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
@RequiredArgsConstructor
public class EntryManualRetryPreProcessingTasklet implements Tasklet {

  private final EntryRepository entryRepository;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
	log.debug("Starting entry manual retry preprocessing step");
	Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
	String originator = (String) jobParameters.get("originator");
	Long entryId = (Long) jobParameters.get("entryId");
	int count = entryRepository.markProcessingStarted(originator, EntryStatus.FAILED, EntryStatus.STARTED, entryId);
	if (count == 0) {
	  contribution.setExitStatus(ExitStatus.NOOP);
	} else {
	  log.debug("{} entries selected for manual retry processing by originator {}", count, originator);
	}
	return RepeatStatus.FINISHED;
  }
}