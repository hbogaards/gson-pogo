package gson.pogo

class Parent {

    String name

    static hasMany = [children: Child]

    static constraints = {
    }
}
