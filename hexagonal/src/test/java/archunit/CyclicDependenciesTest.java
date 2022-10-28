package archunit;

import static archunit.ArchUnitTest.APP_PACKAGE;
import static archunit.ArchUnitTest.classesToCheck;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CyclicDependenciesTest {

  @Test
  @DisplayName("application should be free of cycles")
  void applicationShouldBeFreeOfCycles() {
	ArchRule rule = slices()
		.matching(APP_PACKAGE + ".(*)..")
		.should().beFreeOfCycles();
	rule.check(classesToCheck);
  }
}
