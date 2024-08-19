package com.example.demo.spring.batch.batch

import com.example.demo.spring.batch.AbstractBatchSpec
import com.example.demo.spring.batch.batch.starter.BasicEntryBatchJobStarter
import com.example.demo.spring.batch.core.model.Entry
import org.springframework.beans.factory.annotation.Autowired

import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED
import static com.example.demo.spring.batch.core.model.EntryStatus.REGISTERED

class BasicEntryJobSpec extends AbstractBatchSpec {

    @Autowired
    BasicEntryBatchJobStarter basicEntryBatchJobStarter

    def "should start batch job and process entries"() {
        given:
        entryResourceClient.processEntry(_) >> { args -> processEntry(args[0]) }
        List<Long> ids = saveEntries(10, REGISTERED)

        when:
        basicEntryBatchJobStarter.startBatch()

        then:
        List<Entry> processedEntries = entryRepository.findAllById(ids)

        processedEntries.stream().filter(
                entry -> entry.getStatus() == COMPLETED
                        && entry.getProcessingAttempts() == 1L
                        && entry.getErrorType() == null
                        && entry.getErrorCode() == null
        ).count() == 10

        processedEntries.stream().filter(
                entry -> entry.getStatus() == REGISTERED
                        && entry.getProcessingAttempts() == 0L)
                .count() == 0
    }

    private Entry processEntry(Entry entry) {
        return entry
    }
}
