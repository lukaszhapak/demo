package com.example.demo.test.unit.archunit

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import spock.lang.Specification

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses

class ArchUnitSpec extends Specification {
    private final JavaClasses allProjectClasses = new ClassFileImporter()
            .importPackages("com.example.demo.test.unit.archunit")

    def "hexagonal"() {
        expect:
        noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..adapter..")
                .check(allProjectClasses)
    }

    def "layered"() {
        expect:
        noClasses().that().resideInAPackage("..controller..")
                .should().dependOnClassesThat().resideInAPackage("..repository..")
                .check(allProjectClasses)

        noClasses().that().resideInAPackage("..service..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(allProjectClasses)

        noClasses().that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..controller..")
                .check(allProjectClasses)

        noClasses().that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..service..")
                .check(allProjectClasses)
    }
}
