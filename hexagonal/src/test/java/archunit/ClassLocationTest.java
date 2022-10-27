package archunit;


import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import static archunit.ArchUnitTest.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ClassLocationTest {

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
    }

    @Test
    @DisplayName("there should be no spring dependent classes in core package")
    void thereShouldBeNoSpringDependentClassesInCorePackage() {
        ArchRule rule = noClasses()
                .that().resideInAPackage(CORE_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(SPRING_PACKAGE);
        rule.check(classesToCheck);
    }
}
