package com.example.demo.spring.batch.batch

import com.example.demo.spring.batch.AbstractBatchSpec
import com.example.demo.spring.batch.batch.starter.EntryManualRetryBatchJobStarter
import com.example.demo.spring.batch.core.model.Entry
import org.springframework.beans.factory.annotation.Autowired

import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED
import static com.example.demo.spring.batch.core.model.EntryStatus.FAILED

class EntryManualJobSpec extends AbstractBatchSpec {

    @Autowired
    EntryManualRetryBatchJobStarter entryManualRetryBatchJobStarter

    def "should start batch job and process entries"() {
        given:
        entryResourceClient.processEntry(_) >> { args -> processEntry(args[0]) }
        Long id = saveEntries(1, FAILED).get(0)

        when:
        entryManualRetryBatchJobStarter.startBatch(id)

        then:
        Entry processedEntry = entryRepository.findById(id).get()

        processedEntry.getStatus() == COMPLETED
        processedEntry.getErrorCode() == null
        processedEntry.getErrorType() == null
        processedEntry.getProcessingAttempts() == 2L
    }

    protected Entry processEntry(Entry entry) {
        return entry
    }
}
