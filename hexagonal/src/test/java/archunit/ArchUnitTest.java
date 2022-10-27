package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

public class ArchUnitTest {

    public static final String APP_PACKAGE = "com.example.demo";
    public static final String API_PACKAGE = APP_PACKAGE + ".api..";
    public static final String ADAPTERS_PACKAGE = APP_PACKAGE + ".adapters..";
    public static final String CORE_PACKAGE = APP_PACKAGE + ".core..";
    public static final String CONFIGURATION_PACKAGE = APP_PACKAGE + ".configuration..";
    public static final String SPRING_PACKAGE = "org.springframework..";

    public static final JavaClasses classesToCheck = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages(APP_PACKAGE);
}
