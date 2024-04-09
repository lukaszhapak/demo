package com.example.demo.test.unit.basics.number;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class NumberServiceWithDependency {

  private final NumberRepository numberRepository;

  public int returningIntFromRepository() {
	log.debug("returning int from repository");
	return numberRepository.returningInt();
  }
}
