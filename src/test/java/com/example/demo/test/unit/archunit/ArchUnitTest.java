package com.example.demo.test.unit.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchUnitTest {
  private final JavaClasses allProjectClasses = new ClassFileImporter()
      .importPackages("com.example.demo.test.unit.archunit");

  @Test
  void hexagonalTest() {
    noClasses().that().resideInAPackage("..domain..")
        .should().dependOnClassesThat().resideInAPackage("..adapter..")
        .check(allProjectClasses);
  }
}
