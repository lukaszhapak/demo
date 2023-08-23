package com.example.batch.resource;

import com.example.batch.batch.exception.BusinessProcessingException;
import com.example.batch.batch.exception.SystemProcessingException;
import com.example.batch.core.model.Entry;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class MockEntryResource {

  private final Random random = new Random();

  public Entry someBusinessLogic(Entry entry) {
	sleep();
	int random = this.random.nextInt(10);
	if (random == 1) {
	  throw new SystemProcessingException();
	}
	if (entry.getData().equals("invalid")) {
	  throw new BusinessProcessingException();
	}
	return entry;
  }

  private void sleep() {
	try {
	  Thread.sleep(10);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
  }
}
