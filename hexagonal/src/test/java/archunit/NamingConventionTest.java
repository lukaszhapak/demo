package archunit;

import static archunit.ArchUnitTest.DOMAIN_PACKAGE;
import static archunit.ArchUnitTest.JOB_PACKAGE;
import static archunit.ArchUnitTest.SPRING_PACKAGE;
import static archunit.ArchUnitTest.classesToCheck;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

class NamingConventionTest {

  @Test
  @DisplayName("controllers should have Endpoint suffix")
  void controllersShouldHaveEndpointSuffix() {
	ArchRule rule = classes()
		.that().areAnnotatedWith(RestController.class)
		.should().haveSimpleNameEndingWith("Endpoint");
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("advices should have Advice suffix")
  void advicesShouldHaveAdviceSuffix() {
	ArchRule rule = classes()
		.that().areAnnotatedWith(RestControllerAdvice.class)
		.should().haveSimpleNameEndingWith("Advice");
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("configuration classes should have Configuration suffix")
  void configurationClassesShouldHaveConfigurationSuffix() {
	ArchRule rule = classes()
		.that().areAnnotatedWith(Configuration.class)
		.should().haveSimpleNameEndingWith("Configuration");
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("repository classes should have Repository suffix")
  void repositoryClassesShouldHaveRepositorySuffix() {
	ArchRule rule = classes()
		.that().areAssignableTo(Repository.class)
		.should().haveSimpleNameEndingWith("Repository");
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("jobs classes should have Job suffix")
  void jobClassesShouldHaveJobSuffix() {
	ArchRule rule = classes()
		.that().resideInAPackage(JOB_PACKAGE)
		.should().haveSimpleNameEndingWith("Job");
	rule.check(classesToCheck);
  }
}
