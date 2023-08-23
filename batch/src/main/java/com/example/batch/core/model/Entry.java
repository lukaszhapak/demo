package com.example.batch.core.model;


import static javax.persistence.GenerationType.IDENTITY;

import com.example.batch.api.dto.EntryDTO;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Entry {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String data;

  @Enumerated(EnumType.STRING)
  private EntryStatus status;

  @Enumerated(EnumType.STRING)
  private EntryErrorType errorType;

  private String originator;
  private LocalDateTime processingTime;

  private Long processingAttempts;
  private Long processingAttemptsLimit;

  private String errorCode;

  public static Entry of(EntryDTO entryDTO) {
	Entry entry = new Entry();
	entry.setData(entryDTO.getData());
	entry.setStatus(EntryStatus.REGISTERED);
	entry.setProcessingAttempts(0L);
	entry.setProcessingAttemptsLimit(5L);
	return entry;
  }

  public EntryDTO toDTO() {
	EntryDTO entryDTO = new EntryDTO();
	entryDTO.setId(id);
	entryDTO.setData(data);
	return entryDTO;
  }
}
