package com.example.batch.batch.tasklet;

import static com.example.batch.core.model.EntryStatus.FAILED;
import static com.example.batch.core.model.EntryStatus.STARTED;

import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
@RequiredArgsConstructor
public class EntryAutomaticRetryPreProcessingTasklet implements Tasklet {

  private final EntryRepository entryRepository;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
	log.debug("Starting entry automatic retry preprocessing step");
	String originator = (String) chunkContext.getStepContext().getJobParameters().get("originator");
	int count = entryRepository.markProcessingStarted(originator, FAILED, STARTED, EntryErrorType.SYSTEM);
	if (count == 0) {
	  contribution.setExitStatus(ExitStatus.NOOP);
	} else {
	  log.debug("{} entries selected for automatic retry processing by originator {}", count, originator);
	}
	return RepeatStatus.FINISHED;
  }
}