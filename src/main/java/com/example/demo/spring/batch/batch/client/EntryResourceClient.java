package com.example.demo.spring.batch.batch.client;

import com.example.demo.spring.batch.batch.exception.BusinessProcessingException;
import com.example.demo.spring.batch.batch.exception.SystemProcessingException;
import com.example.demo.spring.batch.core.model.Entry;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class EntryResourceClient {

  Random random = new Random();

  public Entry processEntry(Entry entry) {
	if (entry.getData().equals("business-exception")) {
	  throw new BusinessProcessingException();
	} else if (random.nextInt(5) == 0) {
	  throw new SystemProcessingException();
	}
	return entry;
  }
}
