package com.example.demo.spring.web.thymeleaf;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class GeneratedService {

  private final GeneratedRepository generatedRepository;

  List<Generated> findAll() {
	return generatedRepository.findAll();
  }

  Generated findById(long id) {
	return generatedRepository.findById(id).orElse(null);
  }

  void save(Generated generated) {
	generatedRepository.save(generated);
  }

  void deleteById(long id) {
	generatedRepository.deleteById(id);
  }
}

