package archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

class ArchUnitTest {

  static final String APP_PACKAGE = "com.example.demo";
  static final String API_PACKAGE = APP_PACKAGE + ".api..";
  static final String ADAPTERS_PACKAGE = APP_PACKAGE + ".adapters..";
  static final String DOMAIN_PACKAGE = APP_PACKAGE + ".domain..";
  static final String CONFIGURATION_PACKAGE = APP_PACKAGE + ".configuration..";
  static final String SPRING_PACKAGE = "org.springframework..";

  public static final JavaClasses classesToCheck = new ClassFileImporter()
	  .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
	  .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
	  .importPackages(APP_PACKAGE);
}
