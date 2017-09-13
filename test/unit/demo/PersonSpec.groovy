package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Person)
class PersonSpec extends Specification {

    void "test that nullable:false exists on properties that are not mentioned in constraints block"() {
        expect:
        Person.constraints['firstName'].hasAppliedConstraint('nullable')
        Person.constraints['firstName'].nullable == false

        and:
        Person.constraints['lastName'].hasAppliedConstraint('nullable')
        Person.constraints['lastName'].nullable == false
    }

    void "test that the implied nullable: false is effective"() {
        when:
        def p = new Person()
        p.validate()

        then:
        p.hasErrors()
        p.errors.errorCount == 2

        and:
        p.errors['firstName'].code == 'nullable'
        p.errors['firstName'].code == 'nullable'
    }
}
