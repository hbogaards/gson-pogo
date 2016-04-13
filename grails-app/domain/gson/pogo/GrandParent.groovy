package gson.pogo

class GrandParent {

    String name

    static hasMany = [children: Parent]

    static constraints = {
    }
}
