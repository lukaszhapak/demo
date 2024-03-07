package com.example.demo.spring.batch.batch.tasklet;

import com.example.demo.spring.batch.core.repository.EntryRepository;
import com.example.demo.spring.batch.core.model.EntryStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

@Slf4j
@RequiredArgsConstructor
public class EntryPreProcessingTasklet implements Tasklet {

  private final EntryRepository entryRepository;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
	log.debug("Starting entry preprocessing step");
	String originator = (String) chunkContext.getStepContext().getJobParameters().get("originator");
	int count = entryRepository.markProcessingStarted(originator, EntryStatus.REGISTERED, EntryStatus.STARTED);
	if (count == 0) {
	  contribution.setExitStatus(ExitStatus.NOOP);
	} else {
	  log.debug("{} entries selected for processing by originator {}", count, originator);
	}
	return RepeatStatus.FINISHED;
  }
}