package archunit;


import static archunit.ArchUnitTest.ADAPTERS_PACKAGE;
import static archunit.ArchUnitTest.API_PACKAGE;
import static archunit.ArchUnitTest.CONFIGURATION_PACKAGE;
import static archunit.ArchUnitTest.DOMAIN_PACKAGE;
import static archunit.ArchUnitTest.SPRING_PACKAGE;
import static archunit.ArchUnitTest.classesToCheck;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

class ClassLocationTest {

  @Test
  @DisplayName("controllers should reside in api package")
  void controllersShouldResideInApiPackage() {
	ArchRule rule = classes()
		.that().areAnnotatedWith(RestController.class)
		.should().resideInAPackage(API_PACKAGE);
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("repositories should reside in adapters package")
  void repositoriesShouldResideInAdaptersPackage() {
	ArchRule rule = classes()
		.that().areAnnotatedWith(Repository.class)
		.or().areAssignableTo(JpaRepository.class)
		.should().resideInAPackage(ADAPTERS_PACKAGE);
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("configuration should reside in configuration package")
  void configurationShouldResideInConfigurationPackage() {
	ArchRule rule = classes()
		.that().areAnnotatedWith(Configuration.class)
		.should().resideInAPackage(CONFIGURATION_PACKAGE);
	rule.check(classesToCheck);
	rule = methods()
		.that().areAnnotatedWith(Bean.class)
		.should().beDeclaredInClassesThat().resideInAPackage(CONFIGURATION_PACKAGE);
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("there should be no spring dependent classes in domain package")
  void thereShouldBeNoSpringDependentClassesInDomainPackage() {
	ArchRule rule = noClasses()
		.that().resideInAPackage(DOMAIN_PACKAGE)
		.should().dependOnClassesThat().resideInAPackage(SPRING_PACKAGE);
	rule.check(classesToCheck);
  }
}
