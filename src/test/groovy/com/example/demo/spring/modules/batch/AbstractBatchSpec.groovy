package com.example.demo.spring.modules.batch

import com.example.demo.commons.RestClient
import com.example.demo.spring.modules.batch.batch.client.EntryResourceClient
import com.example.demo.spring.modules.batch.batch.processor.EntryProcessor
import com.example.demo.spring.modules.batch.config.BatchSpecConfig
import com.example.demo.spring.modules.batch.config.SpecEntryRepository
import com.example.demo.spring.modules.batch.core.model.Entry
import com.example.demo.spring.modules.batch.core.model.EntryStatus
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import java.util.stream.Collectors
import java.util.stream.IntStream

import static com.example.demo.spring.modules.batch.core.model.EntryErrorType.SYSTEM
import static com.example.demo.spring.modules.batch.core.model.EntryStatus.FAILED
import static com.example.demo.spring.modules.batch.core.model.EntryStatus.REGISTERED
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@ActiveProfiles("test")
@Import([BatchApplication.class, BatchSpecConfig])
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class AbstractBatchSpec extends Specification implements RestClient {

    @LocalServerPort
    protected int port

    @Autowired
    protected SpecEntryRepository entryRepository

    @SpringBean
    EntryResourceClient entryResourceClient = Stub()

    @SpringBean(name = "entryProcessor")
    EntryProcessor entryProcessor = Spy(new EntryProcessor(entryResourceClient))

    protected List<Long> saveEntries(int count, EntryStatus status) {
        return IntStream.range(0, count).mapToObj(i -> entryRepository.save(createEntry(status)).getId()).collect(Collectors.toList())
    }

    protected Entry createEntry(EntryStatus status) {
        return Entry.builder()
                .data("test-data")
                .status(status)
                .errorType(status == FAILED ? SYSTEM : null)
                .processingAttemptsLimit(5L)
                .processingAttempts(status == REGISTERED ? 0L : 1L)
                .build()
    }
}
