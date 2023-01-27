package archunit;

import static archunit.ArchUnitTest.classesToCheck;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

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
}
