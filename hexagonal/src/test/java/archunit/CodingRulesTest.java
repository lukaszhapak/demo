package archunit;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import static archunit.ArchUnitTest.classesToCheck;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

public class CodingRulesTest {

    @Test
    @DisplayName("timed annotation can not be applied on private method")
    void timedAnnotationCanNotBeAppliedOnPrivateMethod() {
        ArchRule rule = methods()
                .that().areAnnotatedWith(Transactional.class)
                .or().areAnnotatedWith(Cacheable.class)
                .should().notBePrivate().allowEmptyShould(true);
        rule.check(classesToCheck);
    }

    @Test
    @DisplayName("general coding rules")
    void generalCodingRules() {
        GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS.because("You should use a logger").check(classesToCheck);
        GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS.because("You should name your exceptions").check(classesToCheck);
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME.because("You should not use joda time").check(classesToCheck);
        GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION.because("You should use constructor/setter injection").check(classesToCheck);
    }
}
