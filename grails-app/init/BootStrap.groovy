import gson.pogo.Child
import gson.pogo.GrandParent
import gson.pogo.Parent

class BootStrap {

    def init = { servletContext ->

        def gramps = new GrandParent(name: 'Hendrik')
        def pa = new Parent(name: 'Bernhard')
        gramps.addToChildren(pa)

        def son = new Child(name: 'Claus')
        pa.addToChildren(son)

        gramps.save(flush: true, failOnError: true)
    }

    def destroy = {
    }
}
