package com.example.demo.spring.batch.core.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EntryDTO {

  private Long id;
  private String data;
  private EntryStatus status;
  private EntryErrorType errorType;
  private LocalDateTime processingTime;
  private Long processingAttempts;
  private String errorCode;

}
