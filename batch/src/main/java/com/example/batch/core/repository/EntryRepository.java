package com.example.batch.core.repository;

import com.example.batch.core.model.Entry;
import com.example.batch.core.model.EntryErrorType;
import com.example.batch.core.model.EntryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EntryRepository extends JpaRepository<Entry, Long> {

  @Modifying
  @Query("update Entry set originator = :originator, status = :processingStatus where status = :initStatus")
  int markProcessingStarted(String originator, EntryStatus initStatus, EntryStatus processingStatus);

  @Modifying
  @Query("update Entry set originator = :originator, status = :processingStatus where status = :initStatus and id = :entryId")
  int markProcessingStarted(String originator, EntryStatus initStatus, EntryStatus processingStatus, Long entryId);

  @Modifying
  @Query("update Entry set originator = :originator, status = :processingStatus where status = :initStatus and errorType = :errorType and processingAttempts < processingAttemptsLimit")
  int markProcessingStarted(String originator, EntryStatus initStatus, EntryStatus processingStatus, EntryErrorType errorType);

  @Modifying
  @Query("update Entry set status = :finalStatus, processingAttempts = processingAttempts + 1, errorType = :errorType, errorCode = :errorCode where status = :initStatus and originator = :originator")
  int markProcessingFailed(String originator, EntryStatus initStatus, EntryStatus finalStatus, EntryErrorType errorType, String errorCode);
}
