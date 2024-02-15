package com.example.demo.nonspring.mockito;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class NumberServiceWithDependency {

  private final NumberRepository numberRepository;

  int returningIntFromRepository() {
	log.debug("returning int from repository");
	return numberRepository.returningInt();
  }
}
