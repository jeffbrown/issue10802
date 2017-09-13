package demo

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class WidgetSpec extends Specification {

    void "test that nullable:false exists on properties that are not mentioned in constraints block"() {
        expect:
        Widget.constraintsMap['name'].hasAppliedConstraint('nullable')
        Widget.constraintsMap['name'].nullable == false
    }

    void "test that the implied nullable: false is effective"() {
        when:
        def w = new Widget()
        w.validate()

        then:
        w.hasErrors()
        w.errors.errorCount == 1

        and:
        w.errors['name'].code == 'nullable'
    }
}
