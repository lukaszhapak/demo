package com.example.demo.spring.properties;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class PropertiesService {

  @Value("#{${properties.map}}")
  Map<String, String> mapFromProperties;

  @Value("${properties.list}")
  List<String> listFromProperties;

  @Value("${properties.text}")
  String textFromProperties;
}
