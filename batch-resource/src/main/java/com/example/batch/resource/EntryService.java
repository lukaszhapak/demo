package com.example.batch.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class EntryService {

  ProcessResponseDTO getProcessResponseDTO(EntryDTO entryDTO) {
	ProcessResponseDTO processResponseDTO = new ProcessResponseDTO();
	log.debug("request {}", entryDTO);
	if (entryDTO.getData().equals("system-exception")) {
	  log.debug("response SystemProcessingException");
	  throw new SystemException();
	}
	if (entryDTO.getData().equals("business-exception")) {
	  log.debug("response BusinessProcessingException");
	  throw new ValidationException();
	} else {
	  log.debug("response processed");
	  processResponseDTO.setResponse("processed");
	}
	return processResponseDTO;
  }

}
