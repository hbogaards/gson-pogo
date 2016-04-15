package gson.pogo

import grails.plugin.json.view.JsonViewTemplateEngine
import groovy.json.JsonSlurper
import spock.lang.Specification

/**
 * Created by p277204 on 14-4-2016.
 */
class DomainEnumRenderingSpec extends Specification {

    void "Test rendering a POGO with an enum produces the correct json"() {
        given:"A template that deep renders a POGO"
        def templateEngine = new JsonViewTemplateEngine()
        def template = templateEngine.createTemplate('''
import gson.pogo.Address

model {
    Address address
}

json g.render(address, [deep: true])
''')

        when:"The template is rendered"
        def writer = new StringWriter()

        def address = new Address(street: "Dorpstraat 1", city: "Ons Dorp", state: State.ZH)
        def writable = template.make(address: address)
        writable.writeTo(writer)
        def jsonStr = writer.toString()
        def json = new JsonSlurper().parseText(jsonStr)
        println jsonStr
        then:"The JSON is correct"
        json.street == 'Dorpstraat 1'
        json.city == 'Ons Dorp'
        json.state == 'ZH'
    }

    void "Test rendering a POGO with an enum produces the correct json 2"() {
        given:"A template that deep renders a POGO"
        def templateEngine = new JsonViewTemplateEngine()
        def template = templateEngine.createTemplate('''
import gson.pogo.Address
model {
    Address address
}

json {
  street address.street
  city address.city
  state address.state as String
}
''')

        when:"The template is rendered"
        def writer = new StringWriter()

        def address = new Address(street: "Dorpstraat 1", city: "Ons Dorp", state: State.ZH)
        def writable = template.make(address: address)
        writable.writeTo(writer)
        def jsonStr = writer.toString()
        def json = new JsonSlurper().parseText(jsonStr)
        println jsonStr
        then:"The JSON is correct"
        json.street == 'Dorpstraat 1'
        json.city == 'Ons Dorp'
        json.state == 'ZH'
    }
}
