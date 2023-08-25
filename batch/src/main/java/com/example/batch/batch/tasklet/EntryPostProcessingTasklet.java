package com.example.batch.batch.tasklet;

import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
import com.example.batch.core.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
@RequiredArgsConstructor
public class EntryPostProcessingTasklet implements Tasklet {

  private final EntryRepository entryRepository;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
	log.debug("Starting entry postprocessing step");
	String originator = (String) chunkContext.getStepContext().getJobParameters().get("originator");
	int count = entryRepository.markProcessingFailed(originator, EntryStatus.STARTED, EntryStatus.FAILED, EntryErrorType.SYSTEM, "Processing failure");
	if (count > 0) {
	  log.debug("{} entries marked as failed by originator = {}", count, originator);
	}
	return RepeatStatus.FINISHED;
  }
}
