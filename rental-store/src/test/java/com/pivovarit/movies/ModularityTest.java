package com.pivovarit.movies;

import com.pivovarit.access.Public;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.pivovarit", importOptions = ImportOption.DoNotIncludeTests.class)
class ModularityTest {

    @ArchTest
    private static ArchRule onlyPublicFacade = classes()
      .that()
      .arePublic()
      .and()
      .resideInAPackage("..movies")
      .should()
      .beAnnotatedWith(Public.class)
      .as("Facade should be the only public class in the package");

    @ArchTest
    private static ArchRule warehouseInternalsAccessibleLocally = classes()
      .that()
      .resideInAPackage("..warehouse..")
      .and()
      .arePublic()
      .and()
      .areNotAnnotatedWith(Public.class)
      .should()
      .onlyHaveDependentClassesThat()
      .resideInAPackage("..warehouse..")
      .as("Warehouse internals should be accessible only locally");
}
