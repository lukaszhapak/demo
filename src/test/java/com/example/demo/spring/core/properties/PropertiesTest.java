package com.example.demo.spring.core.properties;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.commons.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PropertiesTest extends AbstractIntegrationTest {

  @Autowired
  PropertiesService propertiesService;

  @Test
  @DisplayName("should get values from properties")
  void shouldGetValuesFromProperties() {
	assertThat(propertiesService.textFromProperties).isEqualTo("John");
	assertThat(propertiesService.listFromProperties).containsExactly("John", "Max");
	assertThat(propertiesService.mapFromProperties.get("name")).isEqualTo("John");
	assertThat(propertiesService.mapFromProperties.get("age")).isEqualTo("25");
  }
}