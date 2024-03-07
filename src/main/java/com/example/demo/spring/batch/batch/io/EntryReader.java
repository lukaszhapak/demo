package com.example.demo.spring.batch.batch.io;

import com.example.demo.spring.batch.batch.exception.BatchItemReaderException;
import com.example.demo.spring.batch.core.model.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JpaPagingItemReader;

@Slf4j
public class EntryReader extends JpaPagingItemReader<Entry> {

  @Override
  protected void doReadPage() {
	try{
	  super.doReadPage();
	} catch (Exception e) {
	  throw new BatchItemReaderException();
	}
  }
}