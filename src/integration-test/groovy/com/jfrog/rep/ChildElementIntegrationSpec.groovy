package com.jfrog.rep

import grails.test.mixin.integration.Integration
import spock.lang.Specification

/**
 * Created by shiranr on 10/04/2016.
 */
@Integration
class ChildElementIntegrationSpec extends Specification {

    def 'add attribute - refresh throws NPE'() {
        setup:
        Element element = new Element(name: 'element')
        element.save(validate: true, failOnError: true)

        when:
        element.addAttribute('attr')
        element.save(validate: true, failOnError: true)

        then:
        element.attributes.size() == 1
        element.attributes.first().name == 'attr'
        element.refresh()
        element.refresh().attributes.size() == 1
        element.refresh().attributes.first().name == 'attr'
    }

    def 'add attribute - flush for object should cascade flush to owner as well - NPE on refresh'() {
        setup:
        Element element = new Element(name: 'element1')
        element.save(flush: true, validate: true, failOnError: true)
        AnotherElement element2 = new AnotherElement(name: 'anotherelement', element: element)
        element2.save(validate: true, failOnError: true)
        element.save(flush: true)

        expect:
        element2.refresh()
        element.refresh()
    }

}
