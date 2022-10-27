package archunit;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;


import static archunit.ArchUnitTest.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class NamingConventionTest {

    @Test
    @DisplayName("controllers should have Endpoint suffix")
    void controllersShouldHaveEndpointSuffix() {
        ArchRule rule = classes()
                .that().areAnnotatedWith(RestController.class)
                .should().haveSimpleNameEndingWith("Endpoint");
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
}
