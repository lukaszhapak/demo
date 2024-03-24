package com.example.demo.spring.core.properties;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PropertiesService {

  @Value("#{${properties.map}}")
  final Map<String, String> mapFromProperties;

  @Value("${properties.list}")
  final List<String> listFromProperties;

  @Value("${properties.text}")
  final String textFromProperties;
}
