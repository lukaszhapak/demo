package archunit;

import static archunit.ArchUnitTest.ADAPTERS_PACKAGE;
import static archunit.ArchUnitTest.API_PACKAGE;
import static archunit.ArchUnitTest.classesToCheck;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClassVisibilityTest {

  @Test
  @DisplayName("classes in api package should not be public")
  void classesInApiPackageShouldNotBePublic() {
	ArchRule rule = classes()
		.that().resideInAPackage(API_PACKAGE)
		.should().notBePublic();
	rule.check(classesToCheck);
  }

  @Test
  @DisplayName("classes in adapters package should not be public")
  void classesInAdaptersPackageShouldNotBePublic() {
	ArchRule rule = classes()
		.that().resideInAPackage(ADAPTERS_PACKAGE)
		.should().notBePublic();
	rule.check(classesToCheck);
  }
}
