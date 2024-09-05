package com.example.demo.spring.modules.batch.core.controller


import com.example.demo.spring.modules.batch.AbstractBatchSpec
import com.example.demo.spring.modules.batch.core.model.EntryDTO
import com.example.demo.spring.modules.batch.core.service.EntryService
import io.restassured.response.Response
import org.springframework.beans.factory.annotation.Autowired

import static com.example.demo.spring.modules.batch.core.model.EntryStatus.REGISTERED
import static javax.servlet.http.HttpServletResponse.SC_OK

class EntryControllerSpec extends AbstractBatchSpec {

    private final String URL = "/api/entry"

    @Autowired
    private EntryService entryService

    def "should post entry"() {
        given:
        EntryDTO entryDTO = createEntryDTO()

        when:
        Response response = postHttpCall(entryDTO, URL, port)
        EntryDTO responseAsEntryDTO = response.as(EntryDTO.class)

        then:
        response.statusCode() == SC_OK
        responseAsEntryDTO.getId() != null
        responseAsEntryDTO.getData() == "test-data"
        responseAsEntryDTO.getStatus() == REGISTERED
    }

    def "should get entry"() {
        given:
        Long id = entryService.postEntry(createEntryDTO()).getId()

        when:
        Response response = getHttpCall(URL + "/" + id, port)
        EntryDTO responseAsEntryDTO = response.as(EntryDTO.class)

        then:
        response.statusCode() == SC_OK
        responseAsEntryDTO.getId() != null
        responseAsEntryDTO.getData() == "test-data"
        responseAsEntryDTO.getStatus() == REGISTERED
    }

    private EntryDTO createEntryDTO() {
        EntryDTO entryDTO = new EntryDTO()
        entryDTO.setData("test-data")
        return entryDTO
    }
}
