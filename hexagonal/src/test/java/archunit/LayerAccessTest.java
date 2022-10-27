package archunit;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static archunit.ArchUnitTest.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class LayerAccessTest {

    @Test
    @DisplayName("classes in api package should not depend on classes from adapters package")
    void classesInApiPackageShouldNotDependOnClassesFromAdaptersPackage() {
        ArchRule rule = classes()
                .that().resideInAPackage(API_PACKAGE)
                .should().dependOnClassesThat().resideOutsideOfPackage(ADAPTERS_PACKAGE);
        rule.check(classesToCheck);
    }

    @Test
    @DisplayName("classes in domain package should not depend on classes from api or adapters packages")
    void classesInDomainPackageShouldNotDependOnClassesFromApiOrAdaptersPackages() {
        ArchRule rule = classes()
                .that().resideInAPackage(CORE_PACKAGE)
                .should().dependOnClassesThat().resideOutsideOfPackage(API_PACKAGE)
                .andShould().dependOnClassesThat().resideOutsideOfPackage(ADAPTERS_PACKAGE);
        rule.check(classesToCheck);
    }

    @Test
    @DisplayName("classes in adapters package should not depend on classes in api package")
    void classesInAdaptersPackageShouldNotDependOnClassesInApiPackage() {
        ArchRule rule = classes()
                .that().resideInAPackage(ADAPTERS_PACKAGE)
                .should().dependOnClassesThat().resideOutsideOfPackage(API_PACKAGE);
        rule.check(classesToCheck);
    }
}
