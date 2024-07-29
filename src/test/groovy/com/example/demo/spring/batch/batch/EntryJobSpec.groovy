package com.example.demo.spring.batch.batch

import com.example.demo.spring.batch.AbstractBatchSpec
import com.example.demo.spring.batch.batch.exception.BusinessProcessingException
import com.example.demo.spring.batch.batch.exception.SystemProcessingException
import com.example.demo.spring.batch.batch.starter.EntryBatchJobStarter
import com.example.demo.spring.batch.core.model.Entry
import org.springframework.beans.factory.annotation.Autowired

import static com.example.demo.spring.batch.core.model.EntryErrorType.BUSINESS
import static com.example.demo.spring.batch.core.model.EntryErrorType.SYSTEM
import static com.example.demo.spring.batch.core.model.EntryStatus.*

class EntryJobSpec extends AbstractBatchSpec {

    @Autowired
    EntryBatchJobStarter entryBatchJobStarter

    def "should start batch job and process entries"() {
        given:
        entryResourceClient.processEntry(_) >> { args -> processEntry(args[0]) }
        List<Long> ids = saveEntries(10, REGISTERED)

        when:
        entryBatchJobStarter.startBatch()

        then:
        List<Entry> processedEntries = entryRepository.findAllById(ids)

        processedEntries.stream().filter(entry -> entry.getId() % 4 == 0)
                .allMatch {
                    it.getStatus() == COMPLETED
                            && it.getErrorCode() == null
                            && it.getErrorType() == null
                            && it.getProcessingAttempts() == 1L
                }

        processedEntries.stream().filter(entry -> entry.getId() % 4 == 1)
                .allMatch {
                    it.getStatus() == FAILED
                            && it.getErrorCode() == "Invalid data"
                            && it.getErrorType() == BUSINESS
                            && it.getProcessingAttempts() == 1L
                }

        processedEntries.stream().filter(entry -> entry.getId() % 4 == 2)
                .allMatch {
                    it.getStatus() == FAILED
                            && it.getErrorCode() == "Rest Failure"
                            && it.getErrorType() == SYSTEM
                            && it.getProcessingAttempts() == 1L
                }

        processedEntries.stream().filter(entry -> entry.getId() % 4 == 3)
                .allMatch {
                    it.getStatus() == FAILED
                            && it.getErrorType() == SYSTEM
                            && it.getErrorCode() == "Test exception"
                            && it.getProcessingAttempts() == 1L
                }
    }

    private Entry processEntry(Entry entry) {
        if (entry.getId() % 4 == 3) {
            throw new RuntimeException("Test exception")
        } else if (entry.getId() % 4 == 2) {
            throw new SystemProcessingException()
        } else if (entry.getId() % 4 == 1) {
            throw new BusinessProcessingException()
        }
        return entry
    }
}