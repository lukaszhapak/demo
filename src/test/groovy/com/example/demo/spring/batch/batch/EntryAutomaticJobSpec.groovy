package com.example.demo.spring.batch.batch

import com.example.demo.spring.batch.AbstractBatchSpec
import com.example.demo.spring.batch.batch.starter.EntryAutomaticRetryBatchJobStarter
import com.example.demo.spring.batch.core.model.Entry
import org.springframework.beans.factory.annotation.Autowired

import static com.example.demo.spring.batch.core.model.EntryStatus.COMPLETED
import static com.example.demo.spring.batch.core.model.EntryStatus.FAILED

class EntryAutomaticJobSpec extends AbstractBatchSpec {

    @Autowired
    EntryAutomaticRetryBatchJobStarter entryAutomaticRetryBatchJobStarter

    def "should start batch job and process entries"() {
        given:
        entryResourceClient.processEntry(_) >> { args -> processEntry(args[0]) }
        List<Long> ids = saveEntries(10, FAILED)
        // todo failed entries with business error type
        // todo not failed entries, processed, or registered
        // todo failed entries with processing attempts above the limit

        when:
        entryAutomaticRetryBatchJobStarter.startBatch()

        then:
        List<Entry> processedEntries = entryRepository.findAllById(ids)

        processedEntries.stream().allMatch {
            it.getStatus() == COMPLETED
                    && it.getErrorCode() == null
                    && it.getErrorType() == null
                    && it.getProcessingAttempts() == 2L
        }
    }

    private Entry processEntry(Entry entry) {
        return entry
    }
}
