package com.example.batch.resource;

import com.example.batch.batch.exception.ProcessingException;
import com.example.batch.core.model.Entry;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class MockEntryResource {

  private final Random random = new Random();

  public Entry someBusinessLogic(Entry entry) {
	sleep();
	int random = this.random.nextInt(10);
	if (random < 3) {
	  throw new ProcessingException();
	} else 	if (random > 8){
	  throw new RuntimeException();
	}
	return entry;
  }

  private void sleep() {
	try {
	  Thread.sleep(1000);
	} catch (InterruptedException e) {
	  throw new RuntimeException(e);
	}
  }
}
