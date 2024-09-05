package com.example.demo.spring.modules.batch.batch

import com.example.demo.spring.modules.batch.AbstractBatchSpec
import com.example.demo.spring.modules.batch.batch.exception.BatchItemReaderException
import com.example.demo.spring.modules.batch.batch.starter.EntryBatchJobStarter
import com.example.demo.spring.modules.batch.core.model.Entry
import org.springframework.beans.factory.annotation.Autowired

import static com.example.demo.spring.modules.batch.core.model.EntryErrorType.SYSTEM
import static com.example.demo.spring.modules.batch.core.model.EntryStatus.FAILED
import static com.example.demo.spring.modules.batch.core.model.EntryStatus.REGISTERED

class EntryJobGlobalFailureSpec extends AbstractBatchSpec {

    @Autowired
    EntryBatchJobStarter entryBatchJobStarter

    def "should start batch job and process entries"() {
        given:
        entryProcessor.process(_) >> { throw new BatchItemReaderException() }
        List<Long> ids = saveEntries(10, REGISTERED)

        when:
        entryBatchJobStarter.startBatch()

        then:
        List<Entry> processedEntries = entryRepository.findAllById(ids)

        processedEntries.stream().allMatch {
            it.getStatus() == FAILED
                    && it.getErrorCode() == "Batch global failure"
                    && it.getErrorType() == SYSTEM
                    && it.getProcessingAttempts() == 1L

        }
    }
}