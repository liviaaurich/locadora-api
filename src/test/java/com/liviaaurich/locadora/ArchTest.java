package com.liviaaurich.locadora;

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
            .importPackages("com.liviaaurich.locadora");

        noClasses()
            .that()
            .resideInAnyPackage("com.liviaaurich.locadora.service..")
            .or()
            .resideInAnyPackage("com.liviaaurich.locadora.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.liviaaurich.locadora.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
