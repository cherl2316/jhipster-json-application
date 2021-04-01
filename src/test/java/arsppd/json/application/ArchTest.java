package arsppd.json.application;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("arsppd.json.application");

        noClasses()
            .that()
            .resideInAnyPackage("arsppd.json.application.service..")
            .or()
            .resideInAnyPackage("arsppd.json.application.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..arsppd.json.application.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
